package androidx2j.parser.view

import androidx2j.parser.AttrParser
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
            .build()

    fun target(value: String): CodeBlock? {
        return when (value) {
            "parent" -> code("0")
            else -> id(value)
        }
    }
}