package androidx2j.parser.view

import androidx2j.parser.AttrParser

/**
 * @author 7hens
 */
object ImageView : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("src") { line("$view.setImageDrawable(\$L)", drawable(it)) }
            .build()
}