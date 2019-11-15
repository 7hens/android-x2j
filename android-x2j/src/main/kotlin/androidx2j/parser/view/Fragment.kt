package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.XmlNode
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock

/**
 * @author 7hens
 */
object Fragment : IView, IView.Parent {
    private var className: String? = null

    override val myParser: AttrParser = FrameLayout.myParser + AttrParser.androidBuilder()
            .add("name") { className = it; AttrParser.NO_CODE }
            .end { fragment(className!!, false) }
            .build()

    override val childParser: AttrParser = FrameLayout.childParser

    fun XmlNode.fragment(className: String, isV4: Boolean): CodeBlock {
        val cActivity = ClassName.get("android.app", "Activity")
        val support = if (isV4) "Support" else ""
        return CodeBlock.builder()
                .add("((\$T) context).get${support}FragmentManager()\n", cActivity)
                .indent()
                .add(".beginTransaction()\n")
                .add(".replace($view.getId(), new \$T())\n", ClassName.bestGuess(className))
                .add(".commit();\n")
                .unindent()
                .build()
    }
}