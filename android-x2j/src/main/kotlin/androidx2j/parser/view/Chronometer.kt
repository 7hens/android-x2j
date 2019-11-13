
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Chronometer : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("android:countDown") {
            // 
            todo()
        }
        .add("android:format") {
            // 
            todo()
        }
        .build()

    
}
  