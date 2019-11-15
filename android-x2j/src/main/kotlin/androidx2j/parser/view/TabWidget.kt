
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TabWidget : IView {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("divider") {
            // setDividerDrawable(Drawable)
            todo()
        }
        .add("tabStripEnabled") {
            // setLeftStripDrawable(int)
            todo()
        }
        .add("tabStripLeft") {
            // setLeftStripDrawable(int)
            todo()
        }
        .add("tabStripRight") {
            // setRightStripDrawable(Drawable)
            todo()
        }
        .build()

    
}
  