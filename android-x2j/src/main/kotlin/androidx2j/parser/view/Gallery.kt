
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Gallery : IView, IView.Parent {
    override val myParser = AbsSpinner.myParser + AttrParser.androidBuilder()
        .add("android:animationDuration") {
            // setAnimationDuration(int)
            todo()
        }
        .add("android:gravity") {
            // setGravity(int)
            todo()
        }
        .add("android:spacing") {
            // setSpacing(int)
            todo()
        }
        .add("android:unselectedAlpha") {
            // setUnselectedAlpha(float)
            todo()
        }
        .build()

    
    override val childParser = AbsSpinner.childParser + AttrParser.androidBuilder()
    .build()
      
}
  