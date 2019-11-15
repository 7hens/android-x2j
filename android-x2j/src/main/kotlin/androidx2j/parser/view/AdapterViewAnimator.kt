package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AdapterViewAnimator : IView {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("animateFirstView") { line("$view.setAnimateFirstView(\$L)", bool(it)) }
            .add("inAnimation") { line("$view.setInAnimation(context, \$L)", anim(it)) }
            .add("loopViews") { todo() }
            .add("outAnimation") { line("$view.setOutAnimation(context, \$L)", anim(it)) }
            .build()


}
  