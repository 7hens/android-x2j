
package androidx2j.parser.view

import androidx2j.parser.AttrParser

object ExpandableListView : IView {
    override val myParser = ListView.myParser + AttrParser.androidBuilder()
        .add("childDivider") {
            // 
            todo()
        }
        .add("childIndicator") {
            // 
            todo()
        }
        .add("childIndicatorEnd") {
            // 
            todo()
        }
        .add("childIndicatorLeft") {
            // 
            todo()
        }
        .add("childIndicatorRight") {
            // 
            todo()
        }
        .add("childIndicatorStart") {
            // 
            todo()
        }
        .add("groupIndicator") {
            // 
            todo()
        }
        .add("indicatorEnd") {
            // 
            todo()
        }
        .add("indicatorLeft") {
            // 
            todo()
        }
        .add("indicatorRight") {
            // 
            todo()
        }
        .add("indicatorStart") {
            // 
            todo()
        }
        .build()

    
}
  