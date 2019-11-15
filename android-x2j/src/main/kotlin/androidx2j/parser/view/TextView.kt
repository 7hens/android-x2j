package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.ClassName

/**
 * @author 7hens
 */
object TextView : IView {
    override val myParser: AttrParser = View.myParser + AttrParser.androidBuilder()

            .add("allowUndo") {
                // setAutoLinkMask(int)
                todo()
            }
            .add("autoLink") {
                // setAutoLinkMask(int)
                todo()
            }
            .add("autoSizeMaxTextSize") {
                // setAutoSizeTextTypeUniformWithConfiguration(int,int,int,int)
                todo()
            }
            .add("autoSizeMinTextSize") {
                // setAutoSizeTextTypeUniformWithConfiguration(int,int,int,int)
                todo()
            }
            .add("autoSizePresetSizes") {
                // setAutoSizeTextTypeUniformWithPresetSizes(int,int)
                todo()
            }
            .add("autoSizeStepGranularity") {
                // setAutoSizeTextTypeUniformWithConfiguration(int,int,int,int)
                todo()
            }
            .add("autoSizeTextType") {
                // setAutoSizeTextTypeWithDefaults(int)
                todo()
            }
            .add("autoText") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("breakStrategy") {
                // setBreakStrategy(int)
                todo()
            }
            .add("bufferType") {
                // setText(int,TextView.BufferType)
                todo()
            }
            .add("capitalize") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("cursorVisible") {
                // setCursorVisible(boolean)
                todo()
            }
            .add("digits") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("drawableBottom") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("drawableEnd") {
                // setCompoundDrawablesRelativeWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("drawableLeft") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("drawablePadding") {
                // setCompoundDrawablePadding(int)
                todo()
            }
            .add("drawableRight") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("drawableStart") {
                // setCompoundDrawablesRelativeWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("drawableTint") {
                // setCompoundDrawableTintList(ColorStateList)
                todo()
            }
            .add("drawableTintMode") {
                // setCompoundDrawableTintMode(PorterDuff.Mode)
                todo()
            }
            .add("drawableTop") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("editable") {
                // setInputExtras(int)
                todo()
            }
            .add("editorExtras") {
                // setInputExtras(int)
                todo()
            }
            .add("elegantTextHeight") {
                // setElegantTextHeight(boolean)
                todo()
            }
            .add("ellipsize") {
                // setEllipsize(TextUtils.TruncateAt)
                todo()
            }
            .add("ems") {
                // setEms(int)
                todo()
            }
            .add("enabled") {
                // setFallbackLineSpacing(boolean)
                todo()
            }
            .add("fallbackLineSpacing") {
                // setFallbackLineSpacing(boolean)
                todo()
            }
            .add("firstBaselineToTopHeight") {
                // setFirstBaselineToTopHeight(int)
                todo()
            }
            .add("fontFamily") {
                // setTypeface(Typeface)
                todo()
            }
            .add("fontFeatureSettings") {
                // setFontFeatureSettings(String)
                todo()
            }
            .add("fontVariationSettings") {
                // setFontVariationSettings(String)
                todo()
            }
            .add("freezesText") {
                // setFreezesText(boolean)
                todo()
            }
            .add("gravity")  { line("$view.setGravity(\$L)", gravity(it)) }
            .add("height") {
                // setHeight(int)
                todo()
            }
            .add("hint") {
                // setHint(int)
                todo()
            }
            .add("hyphenationFrequency") {
                // setHyphenationFrequency(int)
                todo()
            }
            .add("imeActionId") {
                // setImeActionLabel(CharSequence,int)
                todo()
            }
            .add("imeActionLabel") {
                // setImeActionLabel(CharSequence,int)
                todo()
            }
            .add("imeOptions") {
                // setImeOptions(int)
                todo()
            }
            .add("includeFontPadding") {
                // setIncludeFontPadding(boolean)
                todo()
            }
            .add("inputMethod") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("inputType") {
                // setRawInputType(int)
                todo()
            }
            .add("justificationMode") {
                // setLastBaselineToBottomHeight(int)
                todo()
            }
            .add("lastBaselineToBottomHeight") {
                // setLastBaselineToBottomHeight(int)
                todo()
            }
            .add("letterSpacing") {
                // setLetterSpacing(float)
                todo()
            }
            .add("lineHeight") {
                // setLineHeight(int)
                todo()
            }
            .add("lineSpacingExtra") {
                // setLineSpacing(float,float)
                todo()
            }
            .add("lineSpacingMultiplier") {
                // setLineSpacing(float,float)
                todo()
            }
            .add("lines") {
                // setLines(int)
                todo()
            }
            .add("linksClickable") {
                // setLinksClickable(boolean)
                todo()
            }
            .add("marqueeRepeatLimit") {
                // setMarqueeRepeatLimit(int)
                todo()
            }
            .add("maxEms") {
                // setMaxEms(int)
                todo()
            }
            .add("maxHeight") {
                // setMaxHeight(int)
                todo()
            }
            .add("maxLength") {
                // setFilters(InputFilter)
                todo()
            }
            .add("maxLines") {
                // setMaxLines(int)
                todo()
            }
            .add("maxWidth") {
                // setMaxWidth(int)
                todo()
            }
            .add("minEms") {
                // setMinEms(int)
                todo()
            }
            .add("minHeight") {
                // setMinHeight(int)
                todo()
            }
            .add("minLines") {
                // setMinLines(int)
                todo()
            }
            .add("minWidth") {
                // setMinWidth(int)
                todo()
            }
            .add("numeric") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("password") {
                // setTransformationMethod(TransformationMethod)
                todo()
            }
            .add("phoneNumber") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("privateImeOptions") {
                // setPrivateImeOptions(String)
                todo()
            }
            .add("scrollHorizontally") {
                // setHorizontallyScrolling(boolean)
                todo()
            }
            .add("selectAllOnFocus") {
                // setSelectAllOnFocus(boolean)
                todo()
            }
            .add("shadowColor") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("shadowDx") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("shadowDy") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("shadowRadius") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("singleLine") {
                // setTransformationMethod(TransformationMethod)
                todo()
            }
            .add("text") { line("$view.setText(\$L)", string(it)) }
            .add("textAllCaps") {
                // setAllCaps(boolean)
                todo()
            }
            .add("textAppearance") {
                // setTextAppearance(int)
                todo()
            }
            .add("textColor") { line("$view.setTextColor(\$L)", color(it)) }
            .add("textColorHighlight") {
                // setHighlightColor(int)
                todo()
            }
            .add("textColorHint") {
                // setHintTextColor(int)
                todo()
            }
            .add("textColorLink") {
                // setLinkTextColor(int)
                todo()
            }
            .add("textCursorDrawable") {
                // setTextCursorDrawable(int)
                todo()
            }
            .add("textFontWeight") {
                // isTextSelectable()
                todo()
            }
            .add("textIsSelectable") {
                // isTextSelectable()
                todo()
            }
            .add("textScaleX") {
                // setTextScaleX(float)
                todo()
            }
            .add("textSelectHandle") {
                // setTextSelectHandle(Drawable)
                todo()
            }
            .add("textSelectHandleLeft") {
                // setTextSelectHandleLeft(Drawable)
                todo()
            }
            .add("textSelectHandleRight") {
                // setTextSelectHandleRight(int)
                todo()
            }
            .add("textSize") {
                line("$view.setTextSize(\$T.COMPLEX_UNIT_PX, \$L)",
                        ClassName.get("android.util", "TypedValue"), dimen(it))
            }
            .add("textStyle") {
                // setTypeface(Typeface,int)
                todo()
            }
            .add("typeface") {
                // setTypeface(Typeface,int)
                todo()
            }
            .add("width") {
                // setWidth(int)
                todo()
            }
            .build()
}