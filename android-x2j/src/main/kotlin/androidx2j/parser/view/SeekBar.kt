
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object SeekBar : IView {
    override val myParser = AbsSeekBar.myParser + AttrParser.androidBuilder()
        .add("android:thumb") {
            // 
            todo()
        }
        .build()

    
}
  