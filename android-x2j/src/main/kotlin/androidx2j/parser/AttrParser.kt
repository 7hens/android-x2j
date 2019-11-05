package androidx2j.parser

import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
interface AttrParser {
    fun parse(node: XmlNode, name: String, value: String): CodeBlock?

    operator fun plus(parser: AttrParser): AttrParser {
        val self = this
        return object : AttrParser {
            override fun parse(node: XmlNode, name: String, value: String): CodeBlock? {
                return self.parse(node, name, value) ?: parser.parse(node, name, value)
            }
        }
    }

    interface Builder {
        fun add(name: String, action: XmlNode.(String) -> CodeBlock?): Builder
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

                override fun build(): AttrParser {
                    return object : AttrParser {
                        override fun parse(node: XmlNode, name: String, value: String): CodeBlock? {
                            return map[name]?.invoke(node, value)
                        }
                    }
                }
            }
        }

        fun androidBuilder() = builder("android:")

        fun appBuilder() = builder("app:")
    }
}