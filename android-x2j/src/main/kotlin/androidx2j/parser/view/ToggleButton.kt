
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ToggleButton : IView {
    override val myParser = CompoundButton.myParser + AttrParser.androidBuilder()
        .add("disabledAlpha") {
            // 
            todo()
        }
        .add("textOff") {
            // 
            todo()
        }
        .add("textOn") {
            // 
            todo()
        }
        .build()

    
}
  