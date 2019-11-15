
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Switch : IView {
    override val myParser = CompoundButton.myParser + AttrParser.androidBuilder()
        .add("showText") {
            // setShowText(boolean)
            todo()
        }
        .add("splitTrack") {
            // setSplitTrack(boolean)
            todo()
        }
        .add("switchMinWidth") {
            // setSwitchMinWidth(int)
            todo()
        }
        .add("switchPadding") {
            // setSwitchPadding(int)
            todo()
        }
        .add("switchTextAppearance") {
            // setSwitchTextAppearance(Context,int)
            todo()
        }
        .add("textOff") {
            // setTextOff(CharSequence)
            todo()
        }
        .add("textOn") {
            // setTextOn(CharSequence)
            todo()
        }
        .add("textStyle") {
            // setSwitchTypeface(Typeface)
            todo()
        }
        .add("thumb") {
            // setThumbResource(int)
            todo()
        }
        .add("thumbTextPadding") {
            // setThumbTextPadding(int)
            todo()
        }
        .add("thumbTint") {
            // setThumbTintList(ColorStateList)
            todo()
        }
        .add("thumbTintMode") {
            // setThumbTintMode(PorterDuff.Mode)
            todo()
        }
        .add("track") {
            // setTrackResource(int)
            todo()
        }
        .add("trackTint") {
            // setTrackTintList(ColorStateList)
            todo()
        }
        .add("trackTintMode") {
            // setTrackTintMode(PorterDuff.Mode)
            todo()
        }
        .add("typeface") {
            // setSwitchTypeface(Typeface)
            todo()
        }
        .build()

    
}
  