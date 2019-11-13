
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object GridView : IView {
    override val myParser = AbsListView.myParser + AttrParser.androidBuilder()
        .add("android:columnWidth") {
            // setColumnWidth(int)
            todo()
        }
        .add("android:gravity") {
            // setGravity(int)
            todo()
        }
        .add("android:horizontalSpacing") {
            // setHorizontalSpacing(int)
            todo()
        }
        .add("android:numColumns") {
            // setNumColumns(int)
            todo()
        }
        .add("android:stretchMode") {
            // setStretchMode(int)
            todo()
        }
        .add("android:verticalSpacing") {
            // setVerticalSpacing(int)
            todo()
        }
        .build()

    
}
  