package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AnalogClock : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("dial") { todo() }
            .add("hand_hour") { todo() }
            .add("hand_minute") { todo() }
            .build()
}
  