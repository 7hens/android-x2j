
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Gallery : IView, IView.Parent {
    override val myParser = AbsSpinner.myParser + AttrParser.androidBuilder()
        .add("animationDuration") {
            // setAnimationDuration(int)
            todo()
        }
        .add("gravity") {
            // setGravity(int)
            todo()
        }
        .add("spacing") {
            // setSpacing(int)
            todo()
        }
        .add("unselectedAlpha") {
            // setUnselectedAlpha(float)
            todo()
        }
        .build()

    
    override val childParser = AbsSpinner.childParser + AttrParser.androidBuilder()
    .build()
      
}
  