package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object ImageView : IView {
    private val cImageView = ClassName.get("android.widget", "ImageView")

    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("src") { line("$view.setImageDrawable(\$L)", drawable(it)) }
            .add("scaleType") { line("$view.setScaleType(\$L)", scaleType(it)) }
            .build()

    fun scaleType(value: String): CodeBlock? {
        return code("\$T.\$L", cImageView.nestedClass("ScaleType"), constant(value))
    }
}