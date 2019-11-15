
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object ViewStub : IView {
    override val myParser = View.myParser + AttrParser.androidBuilder()
        .add("inflatedId") {
            // setInflatedId(int)
            todo()
        }
        .add("layout") {
            // setLayoutResource(int)
            todo()
        }
        .build()

    
}
  