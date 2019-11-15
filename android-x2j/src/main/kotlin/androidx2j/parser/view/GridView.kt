
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object GridView : IView {
    override val myParser = AbsListView.myParser + AttrParser.androidBuilder()
        .add("columnWidth") {
            // setColumnWidth(int)
            todo()
        }
        .add("gravity") {
            // setGravity(int)
            todo()
        }
        .add("horizontalSpacing") {
            // setHorizontalSpacing(int)
            todo()
        }
        .add("numColumns") {
            // setNumColumns(int)
            todo()
        }
        .add("stretchMode") {
            // setStretchMode(int)
            todo()
        }
        .add("verticalSpacing") {
            // setVerticalSpacing(int)
            todo()
        }
        .build()

    
}
  