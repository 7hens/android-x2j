
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ListView : IView {
    override val myParser = AbsListView.myParser + AttrParser.androidBuilder()
        .add("android:divider") {
            // setDivider(Drawable)
            todo()
        }
        .add("android:dividerHeight") {
            // 
            todo()
        }
        .add("android:entries") {
            // 
            todo()
        }
        .add("android:footerDividersEnabled") {
            // 
            todo()
        }
        .add("android:headerDividersEnabled") {
            // 
            todo()
        }
        .build()

    
}
  