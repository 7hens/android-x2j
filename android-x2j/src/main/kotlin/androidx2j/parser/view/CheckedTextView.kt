
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object CheckedTextView : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("checkMark") {
            // setCheckMarkDrawable(int)
            todo()
        }
        .add("checkMarkTint") {
            // setCheckMarkTintList(ColorStateList)
            todo()
        }
        .add("checkMarkTintMode") {
            // setCheckMarkTintMode(PorterDuff.Mode)
            todo()
        }
        .add("checked") {
            // 
            todo()
        }
        .build()

    
}
  