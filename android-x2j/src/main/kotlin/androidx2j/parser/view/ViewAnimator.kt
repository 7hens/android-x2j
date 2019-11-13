
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ViewAnimator : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("android:animateFirstView") {
            // 
            todo()
        }
        .add("android:inAnimation") {
            // 
            todo()
        }
        .add("android:outAnimation") {
            // 
            todo()
        }
        .build()

    
}
  