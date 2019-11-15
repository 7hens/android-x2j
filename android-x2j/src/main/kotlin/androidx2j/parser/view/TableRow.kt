
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TableRow : IView, IView.Parent {
    override val myParser = LinearLayout.myParser

    override val childParser = LinearLayout.childParser + AttrParser.androidBuilder()
        .add("layout_column") {
            // 
            todo()
        }
        .add("layout_span") {
            // 
            todo()
        }
    .build()
      
}
  