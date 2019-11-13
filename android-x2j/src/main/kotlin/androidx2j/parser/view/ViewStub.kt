
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ViewStub : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
        .add("android:inflatedId") {
            // setInflatedId(int)
            todo()
        }
        .add("android:layout") {
            // setLayoutResource(int)
            todo()
        }
        .build()

    
}
  