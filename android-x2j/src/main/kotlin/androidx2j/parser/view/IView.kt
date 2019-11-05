package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.XmlNode
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
interface IView {
    val myParser: AttrParser

    interface Parent {
        val childParser: AttrParser
    }

    fun parser(parent: Parent): AttrParser {
        return object : AttrParser {
            override fun parse(node: XmlNode, name: String, value: String): CodeBlock? {
                if (name == AttrParser.END) {
                    val codeBuilder = CodeBlock.builder()
                    myParser.parse(node, name, value)?.let { codeBuilder.add(it) }
                    parent.childParser.parse(node, name, value)?.let { codeBuilder.add(it) }
                    return codeBuilder.build()
                }
                return myParser.parse(node, name, value)
                        ?: parent.childParser.parse(node, name, value)
            }
        }
    }

    fun code(format: String = "", vararg args: Any?): CodeBlock {
        return if (format.isEmpty()) {
            AttrParser.NO_CODE
        } else {
            CodeBlock.builder().add(format, *args).build()
        }
    }

    fun line(format: String = "", vararg args: Any?): CodeBlock {
        return if (format.isEmpty()) {
            AttrParser.NO_CODE
        } else {
            CodeBlock.builder().addStatement(format, *args).build()
        }
    }

    fun attrId(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it.startsWith("?attr") -> code("R.attr.$resName")
            it.startsWith("?android:attr") -> code("android.R.attr.$resName")
            else -> null
        }
    }

    fun id(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it.startsWith("@+id/") || it.startsWith("@id/") -> code("R.id.$resName")
            it.startsWith("@android:id/") -> code("android.R.id.$resName")
            else -> null
        }
    }

    fun string(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it.startsWith("@string/") -> code("resources.getString(R.string.$resName)")
            it.startsWith("@android:string/") -> code("resources.getString(android.R.string.$resName)")
            else -> code("\$S", it)
        }
    }

    fun float(it: String): CodeBlock? {
        return code(it + "f")
    }

    fun int(it: String): CodeBlock? {
        return code(it)
    }

    fun color(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it.startsWith("@color/") -> code("resources.getColor(R.color.$resName)")
            it.startsWith("@android:color/") -> code("resources.getColor(android.R.color.$resName)")
            it.startsWith("#") -> code("\$T.parseColor(\$S)", ClassName.get("android.graphics", "Color"), it)
            else -> null
        }
    }

    fun drawable(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it == "@null" -> code("null")
            it.startsWith("@drawable") -> code("resources.getDrawable(R.drawable.$resName)")
            it.startsWith("@android:drawable") -> code("resources.getDrawable(android.R.drawable.$resName)")
            it.startsWith("@mipmap") -> code("resources.getDrawable(R.mipmap.$resName)")
            it.startsWith("@android:mipmap") -> code("resources.getDrawable(android.R.mipmap.$resName)")
            it.startsWith("?") -> code("resources.getDrawable(\$T.getResourceIdFromAttr(\$L))",
                    ClassName.get("dev.android.x2j", "X2J"), attrId(it))
            else -> color(it)?.let { code("new \$T($it)", ClassName.get("android.graphics.drawable", "ColorDrawable")) }
        }
    }

    fun bool(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it == "true" || it == "false" -> code(it)
            it.startsWith("@bool") -> code("resources.getBoolean(R.bool.$resName)")
            it.startsWith("@android:bool") -> code("resources.getBoolean(android.R.bool.$resName)")
            else -> null
        }
    }

    fun layoutId(it: String): CodeBlock? {
        val resName = getResourceName(it)
        return when {
            it.startsWith("@layout") -> code("R.layout.$resName")
            it.startsWith("@android:layout") -> code("android.R.layout.$resName")
            else -> null
        }
    }

    fun dimen(it: String): CodeBlock? {
        val resName = it.substring(it.lastIndexOf("/") + 1)
        return when {
            it.startsWith("@dimen/") -> code("R.dimen.$resName")
            it.startsWith("@android:dimen/") -> code("android.R.dimen.$resName")
            it.startsWith("0") -> code("0")
            it.endsWith("px") -> code(it.substringBefore("px"))
            it.endsWith("dp") || it.endsWith("dip") -> applyDimension(it.substringBefore("d"), "DIP")
            it.endsWith("sp") -> applyDimension(it.substringBefore("sp"), "SP")
            it.endsWith("pt") -> applyDimension(it.substringBefore("pt"), "PT")
            it.endsWith("in") -> applyDimension(it.substringBefore("in"), "IN")
            it.endsWith("mm") -> applyDimension(it.substringBefore("mm"), "MM")
            else -> null
        }
    }

    private fun getResourceName(it: String): String {
        return it.substring(it.lastIndexOf("/") + 1)
    }

    private fun applyDimension(it: String, unit: String): CodeBlock {
        return code("(int) Typedit.applyDimension(\$T.COMPLEX_UNIT_$unit, \$L, displayMetrics)",
                ClassName.get("android.util", "TypedValue"), it)
    }

    fun size(it: String): CodeBlock? {
        val cLayoutParams = ClassName.get("android.view", "ViewGroup", "LayoutParams")
        return when (it) {
            "match_parent", "fill_parent" -> CodeBlock.of("\$T.MATCH_PARENT", cLayoutParams)
            "wrap_content" -> CodeBlock.of("\$T.WRAP_CONTENT", cLayoutParams)
            else -> dimen(it)
        }
    }

    fun visibility(it: String): CodeBlock? {
        val cView = ClassName.get("android.view", "View")
        return when (it) {
            "visible" -> CodeBlock.of("\$T.VISIBLE", cView)
            "invisible" -> CodeBlock.of("\$T.INVISIBLE", cView)
            "gone" -> CodeBlock.of("\$T.GONE", cView)
            else -> null
        }
    }

    fun gravity(it: String): CodeBlock? {
        val cGravity = ClassName.get("android.view", "Gravity")
        val gravities = it.split("|")
        return CodeBlock.of(gravities.joinToString("|") { gravity ->
            when (gravity) {
                "start" -> "\$T.START"
                "end" -> "\$T.END"
                "left" -> "\$T.LEFT"
                "right" -> "\$T.RIGHT"
                "top" -> "\$T.TOP"
                "bottom" -> "\$T.BOTTOM"
                "center" -> "\$T.CENTER"
                "center_vertical" -> "\$T.CENTER_VERTICAL"
                "center_horizontal" -> "\$T.CENTER_HORIZONTAL"
                "fill" -> "\$T.FILL"
                "fill_horizontal" -> "\$T.FILL_HORIZONTAL"
                "fill_vertical" -> "\$T.FILL_VERTICAL"
                "clip_vertical" -> "\$T.CLIP_VERTICAL"
                "clip_horizontal" -> "\$T.CLIP_HORIZONTAL"
                else -> "\$T.LEFT"
            }
        }, *Array(gravities.size) { cGravity })
    }

    fun orientation(it: String): CodeBlock? {
        val cLinearLayout = ClassName.get("android.widget", "LinearLayout")
        return when (it) {
            "horizontal" -> LinearLayout.code("\$T.HORIZONTAL", cLinearLayout)
            "vertical" -> LinearLayout.code("\$T.VERTICAL", cLinearLayout)
            else -> null
        }
    }
}