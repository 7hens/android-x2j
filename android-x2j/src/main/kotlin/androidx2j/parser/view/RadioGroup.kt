
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object RadioGroup : IView, IView.Parent {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("android:checkedButton") {
            // getCheckedRadioButtonId()
            todo()
        }
        .build()

    
    override val childParser = LinearLayout.childParser + AttrParser.androidBuilder()
    .build()
      
}
  