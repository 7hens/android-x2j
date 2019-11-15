
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Spinner : IView {
    override val myParser = AbsSpinner.myParser + AttrParser.androidBuilder()
        .add("dropDownHorizontalOffset") {
            // setDropDownHorizontalOffset(int)
            todo()
        }
        .add("dropDownSelector") {
            // setDropDownVerticalOffset(int)
            todo()
        }
        .add("dropDownVerticalOffset") {
            // setDropDownVerticalOffset(int)
            todo()
        }
        .add("dropDownWidth") {
            // setDropDownWidth(int)
            todo()
        }
        .add("gravity") {
            // setGravity(int)
            todo()
        }
        .add("popupBackground") {
            // setPopupBackgroundResource(int)
            todo()
        }
        .add("prompt") {
            // 
            todo()
        }
        .add("spinnerMode") {
            // 
            todo()
        }
        .build()

    
}
  