
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object SlidingDrawer : IView {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
        .add("allowSingleTap") {
            // 
            todo()
        }
        .add("animateOnClick") {
            // 
            todo()
        }
        .add("bottomOffset") {
            // 
            todo()
        }
        .add("content") {
            // 
            todo()
        }
        .add("handle") {
            // 
            todo()
        }
        .add("orientation") {
            // 
            todo()
        }
        .add("topOffset") {
            // 
            todo()
        }
        .build()

    
}
  