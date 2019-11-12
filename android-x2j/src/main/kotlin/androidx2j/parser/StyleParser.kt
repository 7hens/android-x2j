package androidx2j.parser

import com.squareup.javapoet.CodeBlock
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import javax.xml.parsers.SAXParserFactory

/**
 * @author 7hens
 */
object StyleParser {
    private val styles = mutableMapOf<String, StyleNode>()

    fun load(file: File) {
        val parser = SAXParserFactory.newInstance().newSAXParser()
        parser.parse(file, XmlHandler())
    }

    fun parse(parser: AttrParser, node: XmlNode, style: String): CodeBlock {
        val styleNode = styles[style] ?: return AttrParser.NO_CODE
        val codes = CodeBlock.builder()

        val parentName = styleNode.parentName
        if (parentName != null) {
            codes.add(parse(parser, node, parentName))
        }

        styleNode.attributes.forEach { (name, value) ->
            val code = parser.parse(node, name, value)
            if (code != null) {
                codes.add(code)
            } else if (!name.startsWith("tools:") && !name.startsWith("xmlns:") && name != "style") {
                codes.add("// $name=\"$value\"\n")
            }
        }
        return codes.build()
    }

    private class XmlHandler : DefaultHandler() {
        var currentStyleNode: StyleNode? = null
        var currentItemName: String? = null

        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            super.startElement(uri, localName, qName, attributes)
            when (qName) {
                "style" -> {
                    val name = attributes.getValue("name")
                    val parentName = attributes.getValue("parent")
                            ?: if (name.contains(".")) name.substringBeforeLast(".") else null
                    currentStyleNode = StyleNode(name, parentName)
                    styles[name] = currentStyleNode!!
                }
                "item" -> {
                    if (currentStyleNode != null) {
                        currentItemName = attributes.getValue("name")
                        val name = attributes.getValue("name")
                        currentStyleNode!!.attributes[name] = localName
                    }
                }
            }
        }

        override fun characters(ch: CharArray, start: Int, length: Int) {
            super.characters(ch, start, length)
            if (currentStyleNode != null && currentItemName != null) {
                currentStyleNode!!.attributes[currentItemName!!] = String(ch, start, length)
            }
        }

        override fun endElement(uri: String, localName: String, qName: String) {
            super.endElement(uri, localName, qName)
            when (qName) {
                "style" -> currentStyleNode = null
                "item" -> currentItemName = null
            }
        }

    }

    private data class StyleNode(
            val name: String,
            val parentName: String?,
            val attributes: MutableMap<String, String> = hashMapOf()
    )
}