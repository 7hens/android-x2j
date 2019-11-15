
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object SearchView : IView {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("iconifiedByDefault") {
            // setIconifiedByDefault(boolean)
            todo()
        }
        .add("imeOptions") {
            // setImeOptions(int)
            todo()
        }
        .add("inputType") {
            // setInputType(int)
            todo()
        }
        .add("maxWidth") {
            // setMaxWidth(int)
            todo()
        }
        .add("queryHint") {
            // setQueryHint(CharSequence)
            todo()
        }
        .build()

    
}
  