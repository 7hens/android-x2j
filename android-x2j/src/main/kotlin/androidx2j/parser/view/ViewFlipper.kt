
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ViewFlipper : IView {
    override val myParser = ViewAnimator.myParser + AttrParser.androidBuilder()
        .add("android:autoStart") {
            // 
            todo()
        }
        .add("android:flipInterval") {
            // 
            todo()
        }
        .build()

    
}
  