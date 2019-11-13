
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TabWidget : IView {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("android:divider") {
            // setDividerDrawable(Drawable)
            todo()
        }
        .add("android:tabStripEnabled") {
            // setLeftStripDrawable(int)
            todo()
        }
        .add("android:tabStripLeft") {
            // setLeftStripDrawable(int)
            todo()
        }
        .add("android:tabStripRight") {
            // setRightStripDrawable(Drawable)
            todo()
        }
        .build()

    
}
  