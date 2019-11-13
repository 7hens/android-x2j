
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object SlidingDrawer : IView {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
        .add("android:allowSingleTap") {
            // 
            todo()
        }
        .add("android:animateOnClick") {
            // 
            todo()
        }
        .add("android:bottomOffset") {
            // 
            todo()
        }
        .add("android:content") {
            // 
            todo()
        }
        .add("android:handle") {
            // 
            todo()
        }
        .add("android:orientation") {
            // 
            todo()
        }
        .add("android:topOffset") {
            // 
            todo()
        }
        .build()

    
}
  