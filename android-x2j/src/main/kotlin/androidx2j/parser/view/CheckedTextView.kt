
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object CheckedTextView : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("android:checkMark") {
            // setCheckMarkDrawable(int)
            todo()
        }
        .add("android:checkMarkTint") {
            // setCheckMarkTintList(ColorStateList)
            todo()
        }
        .add("android:checkMarkTintMode") {
            // setCheckMarkTintMode(PorterDuff.Mode)
            todo()
        }
        .add("android:checked") {
            // 
            todo()
        }
        .build()

    
}
  