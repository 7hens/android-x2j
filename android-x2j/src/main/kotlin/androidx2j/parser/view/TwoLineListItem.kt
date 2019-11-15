
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TwoLineListItem : IView {
    override val myParser = RelativeLayout.myParser + AttrParser.androidBuilder()
        .add("mode") {
            // 
            todo()
        }
        .build()

    
}
  