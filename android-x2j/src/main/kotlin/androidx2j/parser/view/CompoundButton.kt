
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object CompoundButton : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("android:button") {
            // setButtonDrawable(Drawable)
            todo()
        }
        .add("android:buttonTint") {
            // setButtonTintList(ColorStateList)
            todo()
        }
        .add("android:buttonTintMode") {
            // setButtonTintMode(PorterDuff.Mode)
            todo()
        }
        .build()

    
}
  