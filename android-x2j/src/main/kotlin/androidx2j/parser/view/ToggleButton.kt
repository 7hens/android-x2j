
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ToggleButton : IView {
    override val myParser = CompoundButton.myParser + AttrParser.androidBuilder()
        .add("android:disabledAlpha") {
            // 
            todo()
        }
        .add("android:textOff") {
            // 
            todo()
        }
        .add("android:textOn") {
            // 
            todo()
        }
        .build()

    
}
  