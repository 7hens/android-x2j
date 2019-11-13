package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.ClassName

/**
 * @author 7hens
 */
object TextView : IView {
    override val myParser: AttrParser = View.myParser + AttrParser.androidBuilder()

            .add("android:allowUndo") {
                // setAutoLinkMask(int)
                todo()
            }
            .add("android:autoLink") {
                // setAutoLinkMask(int)
                todo()
            }
            .add("android:autoSizeMaxTextSize") {
                // setAutoSizeTextTypeUniformWithConfiguration(int,int,int,int)
                todo()
            }
            .add("android:autoSizeMinTextSize") {
                // setAutoSizeTextTypeUniformWithConfiguration(int,int,int,int)
                todo()
            }
            .add("android:autoSizePresetSizes") {
                // setAutoSizeTextTypeUniformWithPresetSizes(int,int)
                todo()
            }
            .add("android:autoSizeStepGranularity") {
                // setAutoSizeTextTypeUniformWithConfiguration(int,int,int,int)
                todo()
            }
            .add("android:autoSizeTextType") {
                // setAutoSizeTextTypeWithDefaults(int)
                todo()
            }
            .add("android:autoText") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("android:breakStrategy") {
                // setBreakStrategy(int)
                todo()
            }
            .add("android:bufferType") {
                // setText(int,TextView.BufferType)
                todo()
            }
            .add("android:capitalize") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("android:cursorVisible") {
                // setCursorVisible(boolean)
                todo()
            }
            .add("android:digits") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("android:drawableBottom") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("android:drawableEnd") {
                // setCompoundDrawablesRelativeWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("android:drawableLeft") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("android:drawablePadding") {
                // setCompoundDrawablePadding(int)
                todo()
            }
            .add("android:drawableRight") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("android:drawableStart") {
                // setCompoundDrawablesRelativeWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("android:drawableTint") {
                // setCompoundDrawableTintList(ColorStateList)
                todo()
            }
            .add("android:drawableTintMode") {
                // setCompoundDrawableTintMode(PorterDuff.Mode)
                todo()
            }
            .add("android:drawableTop") {
                // setCompoundDrawablesWithIntrinsicBounds(int,int,int,int)
                todo()
            }
            .add("android:editable") {
                // setInputExtras(int)
                todo()
            }
            .add("android:editorExtras") {
                // setInputExtras(int)
                todo()
            }
            .add("android:elegantTextHeight") {
                // setElegantTextHeight(boolean)
                todo()
            }
            .add("android:ellipsize") {
                // setEllipsize(TextUtils.TruncateAt)
                todo()
            }
            .add("android:ems") {
                // setEms(int)
                todo()
            }
            .add("android:enabled") {
                // setFallbackLineSpacing(boolean)
                todo()
            }
            .add("android:fallbackLineSpacing") {
                // setFallbackLineSpacing(boolean)
                todo()
            }
            .add("android:firstBaselineToTopHeight") {
                // setFirstBaselineToTopHeight(int)
                todo()
            }
            .add("android:fontFamily") {
                // setTypeface(Typeface)
                todo()
            }
            .add("android:fontFeatureSettings") {
                // setFontFeatureSettings(String)
                todo()
            }
            .add("android:fontVariationSettings") {
                // setFontVariationSettings(String)
                todo()
            }
            .add("android:freezesText") {
                // setFreezesText(boolean)
                todo()
            }
            .add("android:gravity")  { line("$view.setGravity(\$L)", gravity(it)) }
            .add("android:height") {
                // setHeight(int)
                todo()
            }
            .add("android:hint") {
                // setHint(int)
                todo()
            }
            .add("android:hyphenationFrequency") {
                // setHyphenationFrequency(int)
                todo()
            }
            .add("android:imeActionId") {
                // setImeActionLabel(CharSequence,int)
                todo()
            }
            .add("android:imeActionLabel") {
                // setImeActionLabel(CharSequence,int)
                todo()
            }
            .add("android:imeOptions") {
                // setImeOptions(int)
                todo()
            }
            .add("android:includeFontPadding") {
                // setIncludeFontPadding(boolean)
                todo()
            }
            .add("android:inputMethod") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("android:inputType") {
                // setRawInputType(int)
                todo()
            }
            .add("android:justificationMode") {
                // setLastBaselineToBottomHeight(int)
                todo()
            }
            .add("android:lastBaselineToBottomHeight") {
                // setLastBaselineToBottomHeight(int)
                todo()
            }
            .add("android:letterSpacing") {
                // setLetterSpacing(float)
                todo()
            }
            .add("android:lineHeight") {
                // setLineHeight(int)
                todo()
            }
            .add("android:lineSpacingExtra") {
                // setLineSpacing(float,float)
                todo()
            }
            .add("android:lineSpacingMultiplier") {
                // setLineSpacing(float,float)
                todo()
            }
            .add("android:lines") {
                // setLines(int)
                todo()
            }
            .add("android:linksClickable") {
                // setLinksClickable(boolean)
                todo()
            }
            .add("android:marqueeRepeatLimit") {
                // setMarqueeRepeatLimit(int)
                todo()
            }
            .add("android:maxEms") {
                // setMaxEms(int)
                todo()
            }
            .add("android:maxHeight") {
                // setMaxHeight(int)
                todo()
            }
            .add("android:maxLength") {
                // setFilters(InputFilter)
                todo()
            }
            .add("android:maxLines") {
                // setMaxLines(int)
                todo()
            }
            .add("android:maxWidth") {
                // setMaxWidth(int)
                todo()
            }
            .add("android:minEms") {
                // setMinEms(int)
                todo()
            }
            .add("android:minHeight") {
                // setMinHeight(int)
                todo()
            }
            .add("android:minLines") {
                // setMinLines(int)
                todo()
            }
            .add("android:minWidth") {
                // setMinWidth(int)
                todo()
            }
            .add("android:numeric") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("android:password") {
                // setTransformationMethod(TransformationMethod)
                todo()
            }
            .add("android:phoneNumber") {
                // setKeyListener(KeyListener)
                todo()
            }
            .add("android:privateImeOptions") {
                // setPrivateImeOptions(String)
                todo()
            }
            .add("android:scrollHorizontally") {
                // setHorizontallyScrolling(boolean)
                todo()
            }
            .add("android:selectAllOnFocus") {
                // setSelectAllOnFocus(boolean)
                todo()
            }
            .add("android:shadowColor") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("android:shadowDx") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("android:shadowDy") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("android:shadowRadius") {
                // setShadowLayer(float,float,float,int)
                todo()
            }
            .add("android:singleLine") {
                // setTransformationMethod(TransformationMethod)
                todo()
            }
            .add("text") { line("$view.setText(\$L)", string(it)) }
            .add("android:textAllCaps") {
                // setAllCaps(boolean)
                todo()
            }
            .add("android:textAppearance") {
                // setTextAppearance(int)
                todo()
            }
            .add("textColor") { line("$view.setTextColor(\$L)", color(it)) }
            .add("android:textColorHighlight") {
                // setHighlightColor(int)
                todo()
            }
            .add("android:textColorHint") {
                // setHintTextColor(int)
                todo()
            }
            .add("android:textColorLink") {
                // setLinkTextColor(int)
                todo()
            }
            .add("android:textCursorDrawable") {
                // setTextCursorDrawable(int)
                todo()
            }
            .add("android:textFontWeight") {
                // isTextSelectable()
                todo()
            }
            .add("android:textIsSelectable") {
                // isTextSelectable()
                todo()
            }
            .add("android:textScaleX") {
                // setTextScaleX(float)
                todo()
            }
            .add("android:textSelectHandle") {
                // setTextSelectHandle(Drawable)
                todo()
            }
            .add("android:textSelectHandleLeft") {
                // setTextSelectHandleLeft(Drawable)
                todo()
            }
            .add("android:textSelectHandleRight") {
                // setTextSelectHandleRight(int)
                todo()
            }
            .add("textSize") {
                line("$view.setTextSize(\$T.COMPLEX_UNIT_PX, \$L)",
                        ClassName.get("android.util", "TypedValue"), dimen(it))
            }
            .add("android:textStyle") {
                // setTypeface(Typeface,int)
                todo()
            }
            .add("android:typeface") {
                // setTypeface(Typeface,int)
                todo()
            }
            .add("android:width") {
                // setWidth(int)
                todo()
            }
            .build()
}