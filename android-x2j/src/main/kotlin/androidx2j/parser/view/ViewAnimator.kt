
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ViewAnimator : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("animateFirstView") {
            // 
            todo()
        }
        .add("inAnimation") {
            // 
            todo()
        }
        .add("outAnimation") {
            // 
            todo()
        }
        .build()

    
}
  