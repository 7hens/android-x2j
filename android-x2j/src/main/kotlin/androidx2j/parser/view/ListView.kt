
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ListView : IView {
    override val myParser = AbsListView.myParser + AttrParser.androidBuilder()
        .add("divider") {
            // setDivider(Drawable)
            todo()
        }
        .add("dividerHeight") {
            // 
            todo()
        }
        .add("entries") {
            // 
            todo()
        }
        .add("footerDividersEnabled") {
            // 
            todo()
        }
        .add("headerDividersEnabled") {
            // 
            todo()
        }
        .build()

    
}
  