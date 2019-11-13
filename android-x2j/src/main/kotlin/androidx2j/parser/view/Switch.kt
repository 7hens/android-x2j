
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Switch : IView {
    override val myParser = CompoundButton.myParser + AttrParser.androidBuilder()
        .add("android:showText") {
            // setShowText(boolean)
            todo()
        }
        .add("android:splitTrack") {
            // setSplitTrack(boolean)
            todo()
        }
        .add("android:switchMinWidth") {
            // setSwitchMinWidth(int)
            todo()
        }
        .add("android:switchPadding") {
            // setSwitchPadding(int)
            todo()
        }
        .add("android:switchTextAppearance") {
            // setSwitchTextAppearance(Context,int)
            todo()
        }
        .add("android:textOff") {
            // setTextOff(CharSequence)
            todo()
        }
        .add("android:textOn") {
            // setTextOn(CharSequence)
            todo()
        }
        .add("android:textStyle") {
            // setSwitchTypeface(Typeface)
            todo()
        }
        .add("android:thumb") {
            // setThumbResource(int)
            todo()
        }
        .add("android:thumbTextPadding") {
            // setThumbTextPadding(int)
            todo()
        }
        .add("android:thumbTint") {
            // setThumbTintList(ColorStateList)
            todo()
        }
        .add("android:thumbTintMode") {
            // setThumbTintMode(PorterDuff.Mode)
            todo()
        }
        .add("android:track") {
            // setTrackResource(int)
            todo()
        }
        .add("android:trackTint") {
            // setTrackTintList(ColorStateList)
            todo()
        }
        .add("android:trackTintMode") {
            // setTrackTintMode(PorterDuff.Mode)
            todo()
        }
        .add("android:typeface") {
            // setSwitchTypeface(Typeface)
            todo()
        }
        .build()

    
}
  