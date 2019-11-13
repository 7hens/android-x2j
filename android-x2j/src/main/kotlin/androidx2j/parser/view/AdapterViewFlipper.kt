package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AdapterViewFlipper : IView {
    override val myParser = AdapterViewAnimator.myParser + AttrParser.androidBuilder()
            .add("android:autoStart") { line("$view.setAutoStart(\$L)", bool(it)) }
            .add("android:flipInterval") { line("$view.setFlipInterval(\$L)", int(it)) }
            .build()
}
  