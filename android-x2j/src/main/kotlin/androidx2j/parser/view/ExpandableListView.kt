
package androidx2j.parser.view

import androidx2j.parser.AttrParser

object ExpandableListView : IView {
    override val myParser = ListView.myParser + AttrParser.androidBuilder()
        .add("android:childDivider") {
            // 
            todo()
        }
        .add("android:childIndicator") {
            // 
            todo()
        }
        .add("android:childIndicatorEnd") {
            // 
            todo()
        }
        .add("android:childIndicatorLeft") {
            // 
            todo()
        }
        .add("android:childIndicatorRight") {
            // 
            todo()
        }
        .add("android:childIndicatorStart") {
            // 
            todo()
        }
        .add("android:groupIndicator") {
            // 
            todo()
        }
        .add("android:indicatorEnd") {
            // 
            todo()
        }
        .add("android:indicatorLeft") {
            // 
            todo()
        }
        .add("android:indicatorRight") {
            // 
            todo()
        }
        .add("android:indicatorStart") {
            // 
            todo()
        }
        .build()

    
}
  