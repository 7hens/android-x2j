
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TimePicker : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("android:timePickerMode") {
            // 
            todo()
        }
        .build()

    
}
  