
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object RatingBar : IView {
    override val myParser = AbsSeekBar.myParser + AttrParser.androidBuilder()
        .add("isIndicator") {
            // setIsIndicator(boolean)
            todo()
        }
        .add("numStars") {
            // 
            todo()
        }
        .add("rating") {
            // 
            todo()
        }
        .add("stepSize") {
            // 
            todo()
        }
        .build()

    
}
  