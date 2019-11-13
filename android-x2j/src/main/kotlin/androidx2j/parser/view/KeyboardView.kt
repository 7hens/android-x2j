
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object KeyboardView : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
        .add("android:keyBackground") {
            // 
            todo()
        }
        .add("android:keyPreviewHeight") {
            // 
            todo()
        }
        .add("android:keyPreviewLayout") {
            // 
            todo()
        }
        .add("android:keyPreviewOffset") {
            // 
            todo()
        }
        .add("android:keyTextColor") {
            // 
            todo()
        }
        .add("android:keyTextSize") {
            // 
            todo()
        }
        .add("android:labelTextSize") {
            // 
            todo()
        }
        .add("android:popupLayout") {
            // 
            todo()
        }
        .add("android:verticalCorrection") {
            // 
            todo()
        }
        .build()

    
}
  