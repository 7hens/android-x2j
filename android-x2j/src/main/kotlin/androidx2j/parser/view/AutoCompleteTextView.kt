package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AutoCompleteTextView : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("android:completionHint") { line("$view.setCompletionHint(\$L)", string(it)) }
            .add("android:completionHintView") { todo() }
            .add("android:completionThreshold") { line("$view.setThreshold(\$L)", int(it)) }
            .add("android:dropDownAnchor") { line("$view.setDropDownAnchor(\$L)", id(it)) }
            .add("android:dropDownHeight") { line("$view.setDropDownHeight(\$L)", dimen(it)) }
            .add("android:dropDownHorizontalOffset") { line("$view.setDropDownHorizontalOffset(\$L)", dimen(it)) }
            .add("android:dropDownSelector") { todo() }
            .add("android:dropDownVerticalOffset") { line("$view.setDropDownVerticalOffset(\$L)", dimen(it)) }
            .add("android:dropDownWidth") { line("$view.setDropDownWidth(\$L)", dimen(it)) }
            .add("android:popupBackground") { line("$view.setDropDownBackgroundDrawable(\$L)", drawable(it)) }
            .build()


}
  