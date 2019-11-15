package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object ViewGroup : IView, IView.Parent {

    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("addStatesFromChildren") {
                // setLayoutTransition(LayoutTransition)
                todo()
            }
            .add("alwaysDrawnWithCache") {
                // setLayoutTransition(LayoutTransition)
                todo()
            }
            .add("animateLayoutChanges") {
                // setLayoutTransition(LayoutTransition)
                todo()
            }
            .add("animationCache") {
                // setClipChildren(boolean)
                todo()
            }
            .add("clipChildren") { line("$view.setClipChildren(\$L)", bool(it)) }
            .add("clipToPadding") { line("$view.setClipToPadding(\$L)", bool(it)) }
            .add("descendantFocusability") {
                // setLayoutMode(int)
                todo()
            }
            .add("layoutAnimation") {
                // setLayoutMode(int)
                todo()
            }
            .add("layoutMode") {
                // setLayoutMode(int)
                todo()
            }
            .add("persistentDrawingCache") {
                // setMotionEventSplittingEnabled(boolean)
                todo()
            }
            .add("splitMotionEvents") {
                // setMotionEventSplittingEnabled(boolean)
                todo()
            }
            .build()

    override val childParser = AttrParser.androidBuilder()
            .add("layout_width") { line("$layout.width = \$L", size(it)) }
            .add("layout_height") { line("$layout.height = \$L", size(it)) }
            .add("layout_margin") {
                val v = dimen(it)
                line("$layout.setMargins(\$L, \$L, \$L, \$L)", v, v, v, v)
            }
            .add("layout_marginHorizontal") {
                val v = dimen(it)
                CodeBlock.builder()
                        .add(line("$layout.leftMargin = \$L", v))
                        .add(line("$layout.rightMargin = \$L", v))
                        .build()
            }
            .add("layout_marginVertical") {
                val v = dimen(it)
                CodeBlock.builder()
                        .add(line("$layout.topMargin = \$L", v))
                        .add(line("$layout.bottomMargin = \$L", v))
                        .build()
            }
            .add("layout_marginLeft") { line("$layout.leftMargin = \$L", dimen(it)) }
            .add("layout_marginTop") { line("$layout.topMargin = \$L", dimen(it)) }
            .add("layout_marginRight") { line("$layout.rightMargin = \$L", dimen(it)) }
            .add("layout_marginBottom") { line("$layout.bottomMargin = \$L", dimen(it)) }
            .build()
}