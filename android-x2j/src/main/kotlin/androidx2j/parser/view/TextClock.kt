
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TextClock : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("android:format12Hour") {
            // setFormat12Hour(CharSequence)
            todo()
        }
        .add("android:format24Hour") {
            // setFormat24Hour(CharSequence)
            todo()
        }
        .add("android:timeZone") {
            // setTimeZone(String)
            todo()
        }
        .build()

    
}
  