package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AbsSpinner : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("android:entries") { todo() }
            .build()

    override val childParser: AttrParser = ViewGroup.childParser
}
  