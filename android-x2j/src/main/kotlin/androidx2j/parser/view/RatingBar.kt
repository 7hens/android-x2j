
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object RatingBar : IView {
    override val myParser = AbsSeekBar.myParser + AttrParser.androidBuilder()
        .add("android:isIndicator") {
            // setIsIndicator(boolean)
            todo()
        }
        .add("android:numStars") {
            // 
            todo()
        }
        .add("android:rating") {
            // 
            todo()
        }
        .add("android:stepSize") {
            // 
            todo()
        }
        .build()

    
}
  