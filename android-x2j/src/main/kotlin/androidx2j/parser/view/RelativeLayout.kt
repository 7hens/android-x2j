package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object RelativeLayout : IView, IView.Parent {
    private val cRelativeLayout = ClassName.get("android.widget", "RelativeLayout")
    private val cLayoutParams = cRelativeLayout.nestedClass("LayoutParams")

    private val siblingRules = arrayOf(
            "above",
            "below",
            "toEndOf",
            "toLeftOf",
            "toRightOf",
            "toStartOf",
            "alignBaseline",
            "alignBottom",
            "alignLeft",
            "alignRight",
            "alignStart",
            "alignTop",
            "alignWithParentIfMissing")

    private val parentRules = arrayOf(
            "alignParentBottom",
            "alignParentEnd",
            "alignParentLeft",
            "alignParentRight",
            "alignParentStart",
            "alignParentTop",
            "centerHorizontal",
            "centerVertical",
            "centerInParent")

    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("gravity") { line("$view.setGravity(\$L)", gravity(it)) }
            .add("ignoreGravity") { line("$view.setIgnoreGravity(\$L)", id(it)) }
            .build()

    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
            .apply {
                siblingRules.forEach { rule ->
                    add("layout_$rule") { line("$layout.addRule(\$L, \$L)", ruleVerb(rule), id(it)) }
                }
                parentRules.forEach { rule ->
                    add("layout_$rule") {
                        if (it.toBoolean()) {
                            line("$layout.addRule(\$L)", ruleVerb(rule))
                        } else {
                            line("$layout.removeRule(\$L)", ruleVerb(rule))
                        }
                    }
                }
            }
            .build()

    private fun ruleVerb(value: String): CodeBlock {
        return code("\$T.\$L", cRelativeLayout, constant(value).replace("TO_", ""))
    }
}
