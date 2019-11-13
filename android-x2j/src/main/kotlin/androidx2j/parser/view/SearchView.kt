
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object SearchView : IView {
    override val myParser = LinearLayout.myParser + AttrParser.androidBuilder()
        .add("android:iconifiedByDefault") {
            // setIconifiedByDefault(boolean)
            todo()
        }
        .add("android:imeOptions") {
            // setImeOptions(int)
            todo()
        }
        .add("android:inputType") {
            // setInputType(int)
            todo()
        }
        .add("android:maxWidth") {
            // setMaxWidth(int)
            todo()
        }
        .add("android:queryHint") {
            // setQueryHint(CharSequence)
            todo()
        }
        .build()

    
}
  