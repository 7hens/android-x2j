
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TextClock : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("format12Hour") {
            // setFormat12Hour(CharSequence)
            todo()
        }
        .add("format24Hour") {
            // setFormat24Hour(CharSequence)
            todo()
        }
        .add("timeZone") {
            // setTimeZone(String)
            todo()
        }
        .build()

    
}
  