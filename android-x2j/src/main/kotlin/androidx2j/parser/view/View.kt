package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object View : IView {
    private var paddingLeft: CodeBlock? = null
    private var paddingTop: CodeBlock? = null
    private var paddingRight: CodeBlock? = null
    private var paddingBottom: CodeBlock? = null

    override val myParser = AttrParser.androidBuilder()
            .add("id") { line("$view.setId(\$L)", id(it)) }
            .add("background") { line("$view.setBackground(\$L)", drawable(it)) }
            .add("visibility") { line("$view.setVisibility(\$L)", visibility(it)) }
            .add("clickable") { line("$view.setClickable(\$L)", bool(it)) }
            .add("contentDescription") { line("$view.setContentDescription(\$L)", string(it)) }
            .add("alpha") { line("$view.setAlpha(\$L)", float(it)) }
            .add("autofillHints") { line("$view.setAutofillHints(\$L)", string(it)) }
            .add("minHeight") { line("$view.setMinimumHeight(\$L)", string(it)) }
            .add("paddingLeft") { paddingLeft = dimen(it); AttrParser.NO_CODE }
            .add("paddingTop") { paddingTop = dimen(it); AttrParser.NO_CODE }
            .add("paddingRight") { paddingRight = dimen(it); AttrParser.NO_CODE }
            .add("paddingBottom") { paddingBottom = dimen(it); AttrParser.NO_CODE }
            .build() + AttrParser.builder()
            .add(AttrParser.END) {
                val codeBuilder = CodeBlock.builder()
                if (paddingLeft ?: paddingRight ?: paddingRight ?: paddingBottom != null) {
                    val v0 = code("0")
                    codeBuilder.add(line("$view.setPadding(\$L, \$L, \$L, \$L)",
                            paddingLeft ?: v0, paddingTop ?: v0,
                            paddingRight ?: v0, paddingBottom ?: v0))
                }
                paddingLeft = null
                paddingTop = null
                paddingRight = null
                paddingBottom = null
                codeBuilder.build()
            }
            .build()
}