package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.Codes
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

    fun todo(): CodeBlock? {
        return null
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

    fun or(value: String, factory: (String) -> CodeBlock?): CodeBlock {
        return value.split("|")
                .fold(CodeBlock.builder().add("0")) { codes, item ->
                    factory.invoke(item)?.let { codes.add("|").add(it) }
                    codes
                }
                .build()
    }

    fun constant(value: String): String {
        val result = StringBuilder()
        var lastCharIsCapital = false
        for (ch in value) {
            val isCapital = ch in 'A'..'Z'
            if (isCapital && !lastCharIsCapital) {
                result.append("_")
            }
            result.append(ch.toUpperCase())
            lastCharIsCapital = isCapital
        }
        return result.toString()
    }

    fun attrId(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value.startsWith("?attr") -> code("R.attr.$resName")
            value.startsWith("?android:attr") -> code("android.R.attr.$resName")
            else -> null
        }
    }

    fun id(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value.startsWith("@+id/") || value.startsWith("@id/") -> code("R.id.$resName")
            value.startsWith("@android:id/") -> code("android.R.id.$resName")
            else -> null
        }
    }

    fun string(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value.startsWith("@string/") -> code("resources.getString(R.string.$resName)")
            value.startsWith("@android:string/") -> code("resources.getString(android.R.string.$resName)")
            else -> code("\$S", value)
        }
    }

    fun float(value: String): CodeBlock? {
        return code(value + "f")
    }

    fun int(value: String): CodeBlock? {
        return code(value)
    }

    fun color(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value.startsWith("@color/") -> code("resources.getColor(R.color.$resName)")
            value.startsWith("@android:color/") -> code("resources.getColor(android.R.color.$resName)")
            value.startsWith("#") -> code("\$T.parseColor(\$S)", ClassName.get("android.graphics", "Color"), value)
            else -> null
        }
    }

    fun colorStateList(value: String): CodeBlock? {
        val cColorStateList = ClassName.get("android.content.res", "ColorStateList")
        return code("\$T.valueOf(\$L)", cColorStateList, color(value))
    }

    fun stateListAnimator(value: String): CodeBlock? {
        val cAnimatorInflater = ClassName.get("android.animation", "AnimatorInflater")
        return code("\$T.loadStateListAnimator(context, \$L)", cAnimatorInflater, id(value))
    }

    fun drawable(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value == "@null" -> code("null")
            value.startsWith("@drawable") -> code("resources.getDrawable(R.drawable.$resName)")
            value.startsWith("@android:drawable") -> code("resources.getDrawable(android.R.drawable.$resName)")
            value.startsWith("@mipmap") -> code("resources.getDrawable(R.mipmap.$resName)")
            value.startsWith("@android:mipmap") -> code("resources.getDrawable(android.R.mipmap.$resName)")
            value.startsWith("?") -> code("resources.getDrawable(\$T.getResourceIdFromAttr(\$L))", Codes.Utils, attrId(value))
            else -> color(value)?.let { code("new \$T($value)", ClassName.get("android.graphics.drawable", "ColorDrawable")) }
        }
    }

    fun bool(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value == "true" || value == "false" -> code(value)
            value.startsWith("@bool") -> code("resources.getBoolean(R.bool.$resName)")
            value.startsWith("@android:bool") -> code("resources.getBoolean(android.R.bool.$resName)")
            else -> null
        }
    }

    fun layoutId(value: String): CodeBlock? {
        val resName = getResourceName(value)
        return when {
            value.startsWith("@layout") -> code("R.layout.$resName")
            value.startsWith("@android:layout") -> code("android.R.layout.$resName")
            else -> null
        }
    }

    fun dimen(value: String): CodeBlock? {
        val resName = value.substring(value.lastIndexOf("/") + 1)
        return when {
            value.startsWith("@dimen/") -> code("R.dimen.$resName")
            value.startsWith("@android:dimen/") -> code("android.R.dimen.$resName")
            value.startsWith("0") -> code("0")
            value.endsWith("px") -> code(value.substringBefore("px"))
            value.endsWith("dp") || value.endsWith("dip") -> applyDimension(value.substringBefore("d"), "DIP")
            value.endsWith("sp") -> applyDimension(value.substringBefore("sp"), "SP")
            value.endsWith("pt") -> applyDimension(value.substringBefore("pt"), "PT")
            value.endsWith("in") -> applyDimension(value.substringBefore("in"), "IN")
            value.endsWith("mm") -> applyDimension(value.substringBefore("mm"), "MM")
            else -> null
        }
    }

    private fun getResourceName(value: String): String {
        return value.substring(value.lastIndexOf("/") + 1)
    }

    private fun applyDimension(value: String, unit: String): CodeBlock {
        return code("(int) \$T.applyDimension(\$T.COMPLEX_UNIT_$unit, \$L, displayMetrics)",
                Codes.TypedValue, Codes.TypedValue, value)
    }

    fun size(value: String): CodeBlock? {
        val cLayoutParams = ClassName.get("android.view", "ViewGroup", "LayoutParams")
        return when (value) {
            "match_parent", "fill_parent" -> CodeBlock.of("\$T.MATCH_PARENT", cLayoutParams)
            "wrap_content" -> CodeBlock.of("\$T.WRAP_CONTENT", cLayoutParams)
            else -> dimen(value)
        }
    }

    fun visibility(value: String): CodeBlock? {
        val cView = ClassName.get("android.view", "View")
        return when (value) {
            "visible" -> CodeBlock.of("\$T.VISIBLE", cView)
            "invisible" -> CodeBlock.of("\$T.INVISIBLE", cView)
            "gone" -> CodeBlock.of("\$T.GONE", cView)
            else -> null
        }
    }


    fun gravity(value: String): CodeBlock? {
        return or(value) { item ->
            code("\$T.\$L", ClassName.get("android.view", "Gravity"), constant(item))
        }
    }

    fun orientation(value: String): CodeBlock? {
        val cLinearLayout = ClassName.get("android.widget", "LinearLayout")
        return when (value) {
            "horizontal" -> LinearLayout.code("\$T.HORIZONTAL", cLinearLayout)
            "vertical" -> LinearLayout.code("\$T.VERTICAL", cLinearLayout)
            else -> null
        }
    }
}