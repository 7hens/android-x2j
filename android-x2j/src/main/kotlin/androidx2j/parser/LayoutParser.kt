package androidx2j.parser

import androidx2j.parser.view.View
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import java.util.*
import javax.xml.parsers.SAXParserFactory

/**
 * @author 7hens
 */
object LayoutParser {
    fun parse(file: File): CodeBlock {
        val codes = CodeBlock.builder()
        val parser = SAXParserFactory.newInstance().newSAXParser()
        parser.parse(file, XmlHandler(codes))
        return codes.build()
    }

    private class XmlHandler(private val codes: CodeBlock.Builder) : DefaultHandler() {
        private val stack = Stack<XmlNode>()
        private var nodeIndex = 0

        init {
            stack.push(XmlNode.root)
            val cResources = ClassName.get("android.content.res", "Resources")
            val cDisplayMetrics = ClassName.get("android.util", "DisplayMetrics")
            line("final \$T resources = context.getResources()", cResources)
            line("final \$T displayMetrics = resources.getDisplayMetrics()", cDisplayMetrics)
        }

        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            super.startElement(uri, localName, qName, attributes)
            nodeIndex += 1
            if (qName == "merge" && nodeIndex == 1) {
                line()
                line("if (!attach || root == null) throw new RuntimeException(\$S)",
                        "error with <merge />: invalid ViewGroup root or not attach")
            }
            val parent = run {
                var parent = stack.peek()
                while (parent.tagName == "merge") {
                    parent = parent.parent!!
                }
                parent
            }
            val node = XmlNode.create(nodeIndex, qName, parent)
            stack.push(node)
            if (qName == "merge") return
            line()
            if (qName == "include") {
                val cView = ClassName.get("android.view", "View")
                val layoutId = View.layoutId(attributes.getValue("layout"))
                line("\$T ${node.view} = X2J.inflate(context, \$L, ${parent.view})", cView, layoutId)
                return
            }
            line("\$T ${node.view} = new \$T(context)", node.viewType, node.viewType)
            line("\$T ${node.layout} = new \$T(0, 0)", parent.layoutType, parent.layoutType)
            codes.add(parse(node, attributes))
        }

        override fun endElement(uri: String, localName: String, qName: String) {
            super.endElement(uri, localName, qName)
            val node = stack.pop()
            val upperNode = stack.peek()
            if (upperNode == XmlNode.root) {
                line()
                if (node.tagName == "merge") {
                    line("return root")
                } else {
                    line("if (attach && root != null) root.addView(${node.view})")
                    line("return ${node.view}")
                }
                return
            }
            if (node.tagName in arrayOf("merge", "include")) {
                line()
                return
            }
            line("\$L.setLayoutParams(\$L)", node.view, node.layout)
            line("${node.parent!!.view}.addView(${node.view})")
        }

        private fun line(format: String = "", vararg args: Any?) {
            if (format.isEmpty()) {
                codes.add("\n")
            } else {
                codes.addStatement(format, *args)
            }
        }

        private fun parse(node: XmlNode, attributes: Attributes): CodeBlock? {
            val parser = AttrParserFactory.getParser(node)
            val codes = CodeBlock.builder()

            val style = attributes.getValue("style")
            if (!style.isNullOrEmpty()) {
                codes.add(StyleParser.parse(parser, node, getStyleName(style)))
            }

            for (i in 0 until attributes.length) {
                val name = attributes.getQName(i)
                val value = attributes.getValue(i)
                val code = parser.parse(node, name, value)
                if (code != null) {
                    codes.add(code)
                } else if (!name.startsWith("tools:") && !name.startsWith("xmlns:") && name != "style") {
                    codes.add("// $name=\"$value\"\n")
                }
            }
            codes.add(parser.end(node) ?: AttrParser.NO_CODE)
            return codes.build()
        }

        private fun getStyleName(value: String): String {
            return when {
                value.startsWith("@style") -> value.substring(value.lastIndexOf("/") + 1)
                else -> ""
            }
        }
    }
}