
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object KeyboardView : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
        .add("keyBackground") {
            // 
            todo()
        }
        .add("keyPreviewHeight") {
            // 
            todo()
        }
        .add("keyPreviewLayout") {
            // 
            todo()
        }
        .add("keyPreviewOffset") {
            // 
            todo()
        }
        .add("keyTextColor") {
            // 
            todo()
        }
        .add("keyTextSize") {
            // 
            todo()
        }
        .add("labelTextSize") {
            // 
            todo()
        }
        .add("popupLayout") {
            // 
            todo()
        }
        .add("verticalCorrection") {
            // 
            todo()
        }
        .build()

    
}
  