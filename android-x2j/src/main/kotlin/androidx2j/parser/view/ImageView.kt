package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.view.View.tintMode
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object ImageView : IView {
    private val cImageView = ClassName.get("android.widget", "ImageView")

    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("adjustViewBounds") { line("$view.setAdjustViewBounds(\$L)", bool(it)) }
            .add("baseline") { line("$view.setBaseline(\$L)", dimen(it)) }
            .add("baselineAlignBottom") { line("$view.setBaselineAlignBottom(\$L)", bool(it)) }
            .add("cropToPadding") { line("$view.setCropToPadding(\$L)", bool(it)) }
            .add("maxHeight") { line("$view.setMaxHeight(\$L)", dimen(it)) }
            .add("maxWidth") { line("$view.setMaxWidth(\$L)", dimen(it)) }
            .add("scaleType") { line("$view.setScaleType(\$L)", scaleType(it)) }
            .add("src") { line("$view.setImageDrawable(\$L)", drawable(it)) }
            .add("tint") { line("$view.setImageTintList(\$L)", colorStateList(it)) }
            .add("tintMode") { line("$view.setImageTintMode(\$L)", tintMode(it)) }
            .build()

    fun scaleType(value: String): CodeBlock? {
        return code("\$T.\$L", cImageView.nestedClass("ScaleType"), constant(value))
    }
}