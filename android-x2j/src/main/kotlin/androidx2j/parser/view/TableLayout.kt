
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object TableLayout : IView, IView.Parent {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("android:collapseColumns") {
            // setColumnCollapsed(int,boolean)
            todo()
        }
        .add("android:shrinkColumns") {
            // setShrinkAllColumns(boolean)
            todo()
        }
        .add("android:stretchColumns") {
            // setStretchAllColumns(boolean)
            todo()
        }
        .build()

    
    override val childParser = LinearLayout.childParser + AttrParser.androidBuilder()
    .build()
      
}
  