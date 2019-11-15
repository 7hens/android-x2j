package androidx2j.parser

import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
interface AttrParser {
    fun parse(node: XmlNode, name: String, value: String): CodeBlock?

    fun end(node: XmlNode): CodeBlock?

    operator fun plus(parser: AttrParser): AttrParser {
        val self = this
        return object : AttrParser {
            override fun parse(node: XmlNode, name: String, value: String): CodeBlock? {
                return self.parse(node, name, value) ?: parser.parse(node, name, value)
            }

            override fun end(node: XmlNode): CodeBlock? {
                return CodeBlock.builder()
                        .add(self.end(node) ?: NO_CODE)
                        .add(parser.end(node) ?: NO_CODE)
                        .build()
            }
        }
    }

    interface Builder {
        fun add(name: String, action: XmlNode.(String) -> CodeBlock?): Builder
        fun end(action: XmlNode.() -> CodeBlock?): Builder
        fun build(): AttrParser
    }

    companion object {
        const val END = ""
        val NO_CODE = CodeBlock.builder().build()

        fun builder(prefix: String = ""): Builder {
            return object : Builder {
                private val map = mutableMapOf<String, (XmlNode, String) -> CodeBlock?>()

                override fun add(name: String, action: XmlNode.(String) -> CodeBlock?): Builder {
                    map[prefix + name] = action
                    return this
                }

                override fun end(action: XmlNode.() -> CodeBlock?): Builder {
                    map[""] = { node, _ -> action.invoke(node) }
                    return this
                }

                override fun build(): AttrParser {
                    return object : AttrParser {
                        override fun parse(node: XmlNode, name: String, value: String): CodeBlock? {
                            return map[name]?.invoke(node, value)
                        }

                        override fun end(node: XmlNode): CodeBlock? {
                            return map[""]?.invoke(node, "")
                        }
                    }
                }
            }
        }

        fun androidBuilder() = builder("android:")

        fun appBuilder() = builder("app:")

        fun xmlnsBuilder() = builder("xmlns:")

        fun toolsBuilder() = builder("tools:")
    }
}