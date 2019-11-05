package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.ClassName

/**
 * @author 7hens
 */
object TextView : IView {
    override val myParser: AttrParser = View.myParser + AttrParser.androidBuilder()
            .add("text") { line("$view.setText(\$L)", string(it)) }
            .add("textColor") { line("$view.setTextColor(\$L)", color(it)) }
            .add("textSize") {
                line("$view.setTextSize(\$T.COMPLEX_UNIT_PX, \$L)",
                        ClassName.get("android.util", "TypedValue"), dimen(it))
            }
            .build()
}