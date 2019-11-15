package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AutoCompleteTextView : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("completionHint") { line("$view.setCompletionHint(\$L)", string(it)) }
            .add("completionHintView") { todo() }
            .add("completionThreshold") { line("$view.setThreshold(\$L)", int(it)) }
            .add("dropDownAnchor") { line("$view.setDropDownAnchor(\$L)", id(it)) }
            .add("dropDownHeight") { line("$view.setDropDownHeight(\$L)", dimen(it)) }
            .add("dropDownHorizontalOffset") { line("$view.setDropDownHorizontalOffset(\$L)", dimen(it)) }
            .add("dropDownSelector") { todo() }
            .add("dropDownVerticalOffset") { line("$view.setDropDownVerticalOffset(\$L)", dimen(it)) }
            .add("dropDownWidth") { line("$view.setDropDownWidth(\$L)", dimen(it)) }
            .add("popupBackground") { line("$view.setDropDownBackgroundDrawable(\$L)", drawable(it)) }
            .build()


}
  