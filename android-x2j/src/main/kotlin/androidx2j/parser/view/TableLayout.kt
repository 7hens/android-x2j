
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TableLayout : IView, IView.Parent {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("collapseColumns") {
            // setColumnCollapsed(int,boolean)
            todo()
        }
        .add("shrinkColumns") {
            // setShrinkAllColumns(boolean)
            todo()
        }
        .add("stretchColumns") {
            // setStretchAllColumns(boolean)
            todo()
        }
        .build()

    
    override val childParser = LinearLayout.childParser + AttrParser.androidBuilder()
    .build()
      
}
  