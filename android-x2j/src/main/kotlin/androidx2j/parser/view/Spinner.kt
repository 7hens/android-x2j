
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Spinner : IView {
    override val myParser = AbsSpinner.myParser + AttrParser.androidBuilder()
        .add("android:dropDownHorizontalOffset") {
            // setDropDownHorizontalOffset(int)
            todo()
        }
        .add("android:dropDownSelector") {
            // setDropDownVerticalOffset(int)
            todo()
        }
        .add("android:dropDownVerticalOffset") {
            // setDropDownVerticalOffset(int)
            todo()
        }
        .add("android:dropDownWidth") {
            // setDropDownWidth(int)
            todo()
        }
        .add("android:gravity") {
            // setGravity(int)
            todo()
        }
        .add("android:popupBackground") {
            // setPopupBackgroundResource(int)
            todo()
        }
        .add("android:prompt") {
            // 
            todo()
        }
        .add("android:spinnerMode") {
            // 
            todo()
        }
        .build()

    
}
  