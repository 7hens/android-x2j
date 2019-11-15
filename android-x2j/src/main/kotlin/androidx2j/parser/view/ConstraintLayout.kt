package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.XmlNode
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object ConstraintLayout : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .build()

    override val childParser = ViewGroup.childParser + AttrParser.appBuilder()
            .add("layout_constraintLeft_toLeftOf") { line("$layout.leftToLeft = \$L", target(it)) }
            .add("layout_constraintLeft_toRightOf") { line("$layout.leftToRight = \$L", target(it)) }
            .add("layout_constraintRight_toLeftOf") { line("$layout.rightToLeft = \$L", target(it)) }
            .add("layout_constraintRight_toRightOf") { line("$layout.rightToRight = \$L", target(it)) }
            .add("layout_constraintTop_toTopOf") { line("$layout.topToTop = \$L", target(it)) }
            .add("layout_constraintTop_toBottomOf") { line("$layout.topToBottom = \$L", target(it)) }
            .add("layout_constraintBottom_toTopOf") { line("$layout.bottomToTop = \$L", target(it)) }
            .add("layout_constraintBottom_toBottomOf") { line("$layout.bottomToBottom = \$L", target(it)) }
            .add("layout_constraintStart_toStartOf") { line("$layout.startToStart = \$L", target(it)) }
            .add("layout_constraintStart_toEndOf") { line("$layout.startToEnd = \$L", target(it)) }
            .add("layout_constraintEnd_toStartOf") { line("$layout.endToStart = \$L", target(it)) }
            .add("layout_constraintEnd_toEndOf") { line("$layout.endToEnd = \$L", target(it)) }
            .add("layout_constraintBaseline_toBaselineOf") { line("$layout.baselineToBaseline = \$L", target(it)) }
            .add("layout_constraintDimensionRatio") { line("$layout.dimensionRatio = \$L", string(it)) }
            .add("layout_constrainedHeight") { line("$layout.constrainedHeight = \$L", bool(it)) }
            .add("layout_constrainedWidth") { line("$layout.constrainedWidth = \$L", bool(it)) }
            .add("layout_constraintHeight_default") { line("$layout.matchConstraintDefaultHeight = \$L", constraintMode(it)) }
            .add("layout_constraintWidth_default") { line("$layout.matchConstraintDefaultWidth = \$L", constraintMode(it)) }
            .add("layout_constraintWidth_min") { line("$layout.matchConstraintMinWidth = '\$L", size(it)) }
            .add("layout_constraintHeight_min") { line("$layout.matchConstraintMinHeight = '\$L", size(it)) }
            .add("layout_constraintWidth_max") { line("$layout.matchConstraintMaxWidth = '\$L", size(it)) }
            .add("layout_constraintHeight_max") { line("$layout.matchConstraintMaxHeight = '\$L", size(it)) }
            .add("layout_constraintHeight_percent") { line("$layout.matchConstraintPercentWidth = \$L", float(it)) }
            .add("layout_constraintWidth_percent") { line("$layout.matchConstraintPercentHeight = \$L", float(it)) }
            .add("layout_constraintVertical_weight") { line("$layout.verticalWeight = \$L", float(it)) }
            .add("layout_constraintHorizontal_weight") { line("$layout.horizontalWeight = \$L", float(it)) }
            .add("layout_constraintVertical_bias") { line("$layout.verticalBias = \$L", float(it)) }
            .add("layout_constraintHorizontal_bias") { line("$layout.horizontalBias = \$L", float(it)) }
            .add("layout_constraintVertical_chainStyle") { line("$layout.verticalChainStyle = \$L", chainStyle(it)) }
            .add("layout_constraintHorizontal_chainStyle") { line("$layout.horizontalChainStyle = \$L", chainStyle(it)) }
            .add("layout_constraintCircle") { line("$layout.circleConstraint = \$L", id(it)) }
            .add("layout_constraintCircleAngle") { line("$layout.circleAngle = \$L", angle(it)) }
            .add("layout_constraintCircleRadius") { line("$layout.circleRadius = \$L", dimen(it)) }
            .add("layout_goneMarginBottom") { line("$layout.goneBottomMargin = \$L", dimen(it)) }
            .add("layout_goneMarginLeft") { line("$layout.goneLeftMargin = \$L", dimen(it)) }
            .add("layout_goneMarginRight") { line("$layout.goneRightMargin = \$L", dimen(it)) }
            .add("layout_goneMarginTop") { line("$layout.goneTopMargin = \$L", dimen(it)) }
            .add("layout_goneMarginEnd") { line("$layout.goneEndMargin = \$L", dimen(it)) }
            .add("layout_goneMarginStart") { line("$layout.goneStartMargin = \$L", dimen(it)) }
            .add("layout_editor_absoluteX") { line("$layout.editorAbsoluteX = \$L", dimen(it)) }
            .add("layout_editor_absoluteY") { line("$layout.editorAbsoluteY = \$L", dimen(it)) }
            .add("layout_constraintGuide_begin") { line("$layout.guideBegin = \$L", dimen(it)) }
            .add("layout_constraintGuide_end") { line("$layout.guideEnd = \$L", dimen(it)) }
            .add("layout_constraintGuide_percent") { line("$layout.guidePercent = \$L", float(it)) }
            .build()

    private fun target(value: String): CodeBlock? {
        return when (value) {
            "parent" -> code("0")
            else -> id(value)
        }
    }

    private fun XmlNode.constraintMode(value: String): CodeBlock {
        return code("\$T.LayoutParams.MATCH_CONSTRAINT_\$L", parent!!.viewType, constant(value))
    }

    private fun XmlNode.chainStyle(value: String): CodeBlock {
        return code("\$T.LayoutParams.CHAIN_", parent!!.viewType, constant(value));
    }

    private fun angle(value: String): CodeBlock {
        var angle = value.toFloat() % 360f
        if (angle < 0f) {
            angle = (360f - angle) % 360f
        }
        return code("${angle}f")
    }
}