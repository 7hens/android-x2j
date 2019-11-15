package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AdapterViewFlipper : IView {
    override val myParser = AdapterViewAnimator.myParser + AttrParser.androidBuilder()
            .add("autoStart") { line("$view.setAutoStart(\$L)", bool(it)) }
            .add("flipInterval") { line("$view.setFlipInterval(\$L)", int(it)) }
            .build()
}
  