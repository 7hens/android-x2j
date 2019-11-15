
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object CompoundButton : IView {
    override val myParser = TextView.myParser + AttrParser.androidBuilder()
        .add("button") {
            // setButtonDrawable(Drawable)
            todo()
        }
        .add("buttonTint") {
            // setButtonTintList(ColorStateList)
            todo()
        }
        .add("buttonTintMode") {
            // setButtonTintMode(PorterDuff.Mode)
            todo()
        }
        .build()

    
}
  