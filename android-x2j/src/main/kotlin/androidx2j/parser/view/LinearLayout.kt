package androidx2j.parser.view

import androidx2j.parser.AttrParser

/**
 * @author 7hens
 */
object LinearLayout : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("baselineAligned") { line("$view.setBaselineAligned(\$L)", bool(it)) }
            .add("baselineAlignedChildIndex") { line("$view.setBaselineAlignedChildIndex(\$L)", int(it)) }
            .add("divider") { line("$view.setDividerDrawable(\$L)", drawable(it)) }
            .add("gravity") { line("$view.setGravity(\$L)", gravity(it)) }
            .add("measureWithLargestChild") { line("$view.setMeasureWithLargestChild(\$L)", bool(it)) }
            .add("orientation") { line("$view.setOrientation(\$L)", orientation(it)) }
            .add("weightSum") { line("$view.setWeightSum(\$L)", float(it)) }
            .build()

    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
            .add("layout_gravity") { line("$layout.gravity(\$L)", gravity(it)) }
            .add("layout_weight") { line("$layout.weight(\$L)", float(it)) }
            .build()
}