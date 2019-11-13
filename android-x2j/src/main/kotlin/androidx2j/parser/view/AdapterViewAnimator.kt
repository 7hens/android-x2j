package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AdapterViewAnimator : IView {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("android:animateFirstView") { line("$view.setAnimateFirstView(\$L)", bool(it)) }
            .add("android:inAnimation") { line("$view.setInAnimation(context, \$L)", anim(it)) }
            .add("android:loopViews") { todo() }
            .add("android:outAnimation") { line("$view.setOutAnimation(context, \$L)", anim(it)) }
            .build()


}
  