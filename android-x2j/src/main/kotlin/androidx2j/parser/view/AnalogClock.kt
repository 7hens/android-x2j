package androidx2j.parser.view

import androidx2j.parser.AttrParser

object AnalogClock : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
            .add("android:dial") { todo() }
            .add("android:hand_hour") { todo() }
            .add("android:hand_minute") { todo() }
            .build()
}
  