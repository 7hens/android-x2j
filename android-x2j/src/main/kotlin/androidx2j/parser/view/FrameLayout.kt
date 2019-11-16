package androidx2j.parser.view

import androidx2j.parser.AttrParser

/**
 * @author 7hens
 */
object FrameLayout : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("foregroundGravity") { line("$view.setForegroundGravity(\$L)", gravity(it)) }
            .add("measureAllChildren") { line("$view.setMeasureAllChildren(\$L)", bool(it)) }
            .build()

    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
            .add("layout_gravity") { line("$layout.gravity = \$L", gravity(it)) }
            .build()
}