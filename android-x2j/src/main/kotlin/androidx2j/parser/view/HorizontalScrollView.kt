
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object HorizontalScrollView : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("fillViewport") {
            // setFillViewport(boolean)
            todo()
        }
        .build()

    
}
  