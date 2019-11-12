package androidx2j.parser

import androidx2j.parser.view.*
import com.squareup.javapoet.*
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import java.util.*
import javax.lang.model.element.Modifier
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
        private var nodeIndex = 1

        init {
            val cResources = ClassName.get("android.content.res", "Resources")
            val cDisplayMetrics = ClassName.get("android.util", "DisplayMetrics")
            codes.addStatement("final \$T resources = context.getResources()", cResources)
                    .addStatement("final \$T displayMetrics = resources.getDisplayMetrics()", cDisplayMetrics)
        }

        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            super.startElement(uri, localName, qName, attributes)
            val node = XmlNode(nodeIndex++, qName, attributes, stack.firstOrNull())
            stack.push(node)

            line()
            line("\$T \$L = new \$T(context)", node.viewType, node.view, node.viewType)

            val parentType = node.parent?.viewType ?: ClassName.get("android.widget", "FrameLayout")
            val parentLayoutType = parentType.nestedClass("LayoutParams")
            line("\$T \$L = new \$T(0, 0)", parentLayoutType, node.layout, parentLayoutType)

            codes.add(parse(node))
        }

        override fun endElement(uri: String, localName: String, qName: String) {
            super.endElement(uri, localName, qName)
            val node = stack.pop()
            if (stack.isEmpty()) {
                codes.add("\n")
                        .addStatement("return ${node.view}")
            } else {
                val parent = stack.peek()
                codes.addStatement("\$L.setLayoutParams(\$L)", node.view, node.layout)
                        .addStatement("${parent.view}.addView(${node.view})")
            }
        }

        private fun line(format: String = "", vararg args: Any?) {
            if (format.isEmpty()) {
                codes.add("\n")
            } else {
                codes.addStatement(format, *args)
            }
        }

        private fun parse(node: XmlNode): CodeBlock? {
            val parser = getParser(node)
            val attributes = node.attributes
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
            parser.parse(node, AttrParser.END, "")?.let { codes.add(it) }
            return codes.build()
        }

        private val viewMap = mapOf(
                "View" to View,
                "ImageView" to ImageView,
                "TextView" to TextView,
                "Button" to TextView,
                "ViewGroup" to ViewGroup,
                "FrameLayout" to FrameLayout,
                "LinearLayout" to LinearLayout,
                "RelativeLayout" to RelativeLayout,
                "GridLayout" to GridLayout,
                "android.support.constraint.ConstraintLayout" to ConstraintLayout
        )

        private fun getParser(node: XmlNode): AttrParser {
            val view = viewMap[node.tagName] ?: View
            val viewParent = node.parent?.run { viewMap[tagName] as? IView.Parent } ?: ViewGroup
            return view.parser(viewParent)
        }

        private fun getStyleName(value: String): String {
            return when {
                value.startsWith("@style") -> value.substring(value.lastIndexOf("/") + 1)
                else -> ""
            }
        }
    }
}