package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.Codes
import androidx2j.parser.XmlNode
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock

/**
 * [https://developer.android.google.cn/reference/android/view/View]
 *
 * @author 7hens
 */
object View : IView {
    private val cView = Codes.View
    private var paddingLeft: CodeBlock? = null
    private var paddingTop: CodeBlock? = null
    private var paddingRight: CodeBlock? = null
    private var paddingBottom: CodeBlock? = null

    override val myParser = AttrParser.androidBuilder()
            .add("accessibilityHeading") { line("$view.setAccessibilityHeading(\$L)", bool(it)) }
            .add("accessibilityLiveRegion") { line("$view.setAccessibilityLiveRegion(\$L)", accessibilityLiveRegion(it)) }
            .add("accessibilityPaneTitle") { line("$view.setAccessibilityPaneTitle(\$L)", string(it)) }
            .add("accessibilityTraversalAfter") { line("$view.setAccessibilityTraversalAfter(\$L)", int(it)) }
            .add("accessibilityTraversalBefore") { line("$view.setAccessibilityTraversalBefore(\$L)", int(it)) }
            .add("alpha") { line("$view.setAlpha(\$L)", float(it)) }
            .add("autofillHints") { line("$view.setAutofillHints(\$L)", string(it)) }
            .add("background") { line("$view.setBackground(\$L)", drawable(it)) }
            .add("backgroundTint") { line("$view.setBackgroundTintList(\$L)", colorStateList(it)) }
            .add("backgroundTintMode") { line("$view.setBackgroundTintListMode(\$L)", tintMode(it)) }
            .add("clickable") { line("$view.setClickable(\$L)", bool(it)) }
            .add("contentDescription") { line("$view.setContentDescription(\$L)", string(it)) }
            .add("contextClickable") { line("$view.setContextClickable(\$L)", bool(it)) }
            .add("defaultFocusHighlightEnabled") { line("$view.setDefaultFocusHighlightEnabled(\$L)", bool(it)) }
            .add("drawingCacheQuality") { line("$view.setDrawingCacheQuality(\$L)", drawingCacheQuality(it)) }
            .add("duplicateParentState") { line("$view.setDuplicateParentStateEnabled(\$L)", bool(it)) }
            .add("elevation") { line("$view.setElevation(\$L)", dimen(it)) }
            .add("fadeScrollbars") { line("$view.setScrollbarFadingEnabled(\$L)", bool(it)) }
            .add("fadingEdgeLength") { line("$view.setFadingEdgeLength(\$L)", dimen(it)) }
            .add("filterTouchesWhenObscured") { line("$view.setFilterTouchesWhenObscured(\$L)", bool(it)) }
            .add("fitsSystemWindows") { line("$view.setFitsSystemWindows(\$L)", bool(it)) }
            .add("focusable") { line("$view.setFocusable(\$L)", bool(it)) }
            .add("focusableInTouchMode") { line("$view.setFocusableInTouchMode(\$L)", bool(it)) }
            .add("focusedByDefault") { line("$view.setFocusedByDefault(\$L)", bool(it)) }
            .add("forceHasOverlappingRendering") { line("$view.forceHasOverlappingRendering(\$L)", bool(it)) }
            .add("foreground") { line("$view.setForeground(\$L)", drawable(it)) }
            .add("foregroundGravity") { line("$view.setForegroundGravity(\$L)", gravity(it)) }
            .add("foregroundTint") { line("$view.setForegroundTintList(\$L)", colorStateList(it)) }
            .add("foregroundTintMode") { line("$view.setForegroundTintMode(\$L)", tintMode(it)) }
            .add("hapticFeedbackEnabled") { line("$view.setHapticFeedbackEnabled(\$L)", bool(it)) }
            .add("id") { line("$view.setId(\$L)", id(it)) }
            .add("importantForAccessibility") { line("$view.setImportantForAccessibility(\$L)", importantForAccessibility(it)) }
            .add("importantForAutofill") { line("$view.setImportantForAutofill(\$L)", importantForAutofill(it)) }
            .add("isScrollContainer") { line("$view.setScrollContainer(\$L)", bool(it)) }
            .add("keepScreenOn") { line("$view.setKeepScreenOn(\$L)", bool(it)) }
            .add("keyboardNavigationCluster") { line("$view.setKeyboardNavigationCluster(\$L)", bool(it)) }
            .add("layerType") { line("$view.setLayerType(\$L, null)", layerType(it)) }
            .add("layoutDirection") { line("$view.setLayoutDirection(\$L, null)", layoutDirection(it)) }
            .add("longClickable") { line("$view.setLongClickable(\$L, null)", bool(it)) }
            .add("minHeight") { line("$view.setMinimumHeight(\$L)", dimen(it)) }
            .add("minWidth") { line("$view.setMinimumWidth(\$L)", dimen(it)) }
            .add("nextClusterForward") { line("$view.setNextClusterForwardId(\$L)", id(it)) }
            .add("nextFocusDown") { line("$view.setNextFocusDownId(\$L)", id(it)) }
            .add("nextFocusForward") { line("$view.setNextFocusForwardId(\$L)", id(it)) }
            .add("nextFocusLeft") { line("$view.setNextFocusLeftId(\$L)", id(it)) }
            .add("nextFocusRight") { line("$view.setNextFocusRightId(\$L)", id(it)) }
            .add("nextFocusUp") { line("$view.setNextFocusUpId(\$L)", id(it)) }
            .add("onClick") { line("$view.setOnClickListener(\$T.onClickListener($view, \$L))", Codes.Utils, string(it)) }
            .add("outlineAmbientShadowColor") { line("$view.setOutlineAmbientShadowColor(\$L)", color(it)) }
            .add("outlineSpotShadowColor") { line("$view.setOutlineSpotShadowColor(\$L)", color(it)) }
            .add("padding") { padding(dimen(it), true) }
            .add("paddingHorizontal") { padding(dimen(it), left = true, top = false) }
            .add("paddingVertical") { padding(dimen(it), left = false, top = true) }
            .add("paddingLeft") { padding(dimen(it), left = true, top = false, right = false, bottom = false) }
            .add("paddingTop") { padding(dimen(it), left = false, top = true, right = true, bottom = false) }
            .add("paddingRight") { padding(dimen(it), left = false, top = false, right = true, bottom = false) }
            .add("paddingBottom") { padding(dimen(it), left = false, top = false, right = false, bottom = true) }
            .add("paddingStart") { todo() }
            .add("paddingEnd") { todo() }
            .add("requiresFadingEdge") { requiresFadingEdge(it) }
            .add("rotation") { line("$view.setRotation(\$L)", float(it)) }
            .add("rotationX") { line("$view.setRotationX(\$L)", float(it)) }
            .add("rotationY") { line("$view.setRotationY(\$L)", float(it)) }
            .add("saveEnabled") { line("$view.setSaveEnabled(\$L)", bool(it)) }
            .add("scaleX") { line("$view.setScaleX(\$L)", float(it)) }
            .add("scaleY") { line("$view.setScaleY(\$L)", float(it)) }
            .add("screenReaderFocusable") { line("$view.setScreenReaderFocusable(\$L)", bool(it)) }
            .add("scrollIndicators") { line("$view.setScrollIndicators(\$L)", scrollIndicators(it)) }
            .add("scrollX") { line("$view.setScrollX(\$L)", dimen(it)) }
            .add("scrollY") { line("$view.setScrollY(\$L)", dimen(it)) }
            .add("scrollbarAlwaysDrawHorizontalTrack") { todo() }
            .add("scrollbarAlwaysDrawVerticalTrack") { todo() }
            .add("scrollbarDefaultDelayBeforeFade") { line("$view.setScrollbarDefaultDelayBeforeFade(\$L)", int(it)) }
            .add("scrollbarFadeDuration") { line("$view.setScrollbarFadeDuration(\$L)", int(it)) }
            .add("scrollbarSize") { line("$view.setScrollbarSize(\$L)", dimen(it)) }
            .add("scrollbarStyle") { line("$view.setScrollbarStyle(\$L)", scrollBarStyle(it)) }
            .add("scrollbarThumbHorizontal") { line("$view.setHorizontalScrollbarThumbDrawable(\$L)", drawable(it)) }
            .add("scrollbarThumbVertical") { line("$view.setVerticalScrollbarThumbDrawable(\$L)", drawable(it)) }
            .add("scrollbarTrackHorizontal") { line("$view.setHorizontalScrollbarTrackDrawable(\$L)", drawable(it)) }
            .add("scrollbarTrackVertical") { line("$view.setVerticalScrollbarTrackDrawable(\$L)", drawable(it)) }
            .add("scrollbars") { line("\$L", scrollbars(it)) }
            .add("soundEffectsEnabled") { line("$view.setSoundEffectsEnabled(\$L)", bool(it)) }
            .add("stateListAnimator") { line("$view.setStateListAnimator(\$L)", stateListAnimator(it)) }
            .add("tag") { line("$view.setTag(\$L)", string(it)) }
            .add("textAlignment") { line("$view.setTextAlignment(\$L)", textAlignment(it)) }
            .add("textDirection") { line("$view.setTextDirection(\$L)", textDirection(it)) }
            .add("theme") { todo() }
            .add("tooltipText") { line("$view.setTooltipText(\$L)", string(it)) }
            .add("transformPivotX") { line("$view.setTransformPivotX(\$L)", dimen(it)) }
            .add("transformPivotY") { line("$view.setTransformPivotY(\$L)", dimen(it)) }
            .add("transitionName") { line("$view.setTransitionName(\$L)", string(it)) }
            .add("transitionX") { line("$view.setTransitionX(\$L)", dimen(it)) }
            .add("transitionY") { line("$view.setTransitionY(\$L)", dimen(it)) }
            .add("transitionZ") { line("$view.setTransitionZ(\$L)", dimen(it)) }
            .add("visibility") { line("$view.setVisibility(\$L)", visibility(it)) }
            .build() + AttrParser.builder()
            .add(AttrParser.END) { end() }
            .build()

    fun XmlNode.end(): CodeBlock {
        val codeBuilder = CodeBlock.builder()
        if (paddingLeft ?: paddingRight ?: paddingRight ?: paddingBottom != null) {
            val v0 = code("0")
            codeBuilder.add(line("$view.setPadding(\$L, \$L, \$L, \$L)",
                    paddingLeft ?: v0, paddingTop ?: v0,
                    paddingRight ?: v0, paddingBottom ?: v0))
        }
        padding(null, true)
        return codeBuilder.build()
    }

    fun padding(code: CodeBlock?, left: Boolean, top: Boolean = left, right: Boolean = left, bottom: Boolean = top): CodeBlock? {
        if (left) paddingLeft = code
        if (top) paddingTop = code
        if (right) paddingRight = code
        if (bottom) paddingBottom = code
        return AttrParser.NO_CODE
    }

    fun accessibilityLiveRegion(value: String): CodeBlock {
        return when (value) {
            "assertive" -> code("2")
            "polite" -> code("1")
            "none" -> code("0")
            else -> code("0")
        }
    }

    fun importantForAccessibility(value: String): CodeBlock {
        return when (value) {
            "auto" -> code("0")
            "yes" -> code("1")
            "no" -> code("2")
            "noHideDescendants" -> code("4")
            else -> code("0")
        }
    }

    fun importantForAutofill(value: String): CodeBlock {
        return when (value) {
            "auto" -> code("0")
            "yes" -> code("1")
            "no" -> code("2")
            "yesHideDescendants" -> code("4")
            "noHideDescendants" -> code("8")
            else -> code("0")
        }
    }

    fun XmlNode.requiresFadingEdge(value: String): CodeBlock? {
        val codes = CodeBlock.builder()
        value.split("|").forEach { item ->
            when (item) {
                "horizontal" -> codes.addStatement("$view.setHorizontalFadingEdgeEnabled(true)")
                "vertical" -> codes.addStatement("$view.setVerticalFadingEdgeEnabled(true)")
                "none" -> codes.addStatement("$view.setHorizontalFadingEdgeEnabled(false)")
                        .addStatement("$view.setVerticalFadingEdgeEnabled(false)")
            }
        }
        return codes.build()
    }

    fun tintMode(value: String): CodeBlock {
        return code("\$T.\$L", ClassName.get("android.graphics", "PorterDuff", "Mode"), constant(value))
    }

    fun drawingCacheQuality(value: String): CodeBlock {
        return when (value) {
            "auto" -> code("0")
            "low" -> code("1")
            "high" -> code("2")
            else -> code("0")
        }
    }

    fun layerType(value: String): CodeBlock {
        return when (value) {
            "hardware" -> code("2")
            "software" -> code("1")
            "none" -> code("0")
            else -> code("0")
        }
    }

    fun layoutDirection(value: String): CodeBlock {
        return when (value) {
            "inherit" -> code("2")
            "locale" -> code("3")
            "ltr" -> code("0")
            "rtl" -> code("1")
            else -> code("0")
        }
    }

    fun scrollIndicators(value: String): CodeBlock? {
        return or(value) { item ->
            if (item == "none") {
                code("0")
            } else {
                code("\$T.SCROLL_INDICATOR_\$L", cView, constant(value))
            }
        }
    }

    fun scrollBarStyle(value: String): CodeBlock? {
        return code("\$T.SCROLLBARS_\$L", cView, constant(value))
    }

    fun XmlNode.scrollbars(value: String): CodeBlock? {
        val codes = CodeBlock.builder()
        value.split("|").forEach { item ->
            when (item) {
                "horizontal" -> codes.addStatement("$view.setHorizontalScrollBarEnabled(true)")
                "vertical" -> codes.addStatement("$view.setVerticalScrollBarEnabled(true)")
                "none" -> codes.addStatement("$view.setHorizontalScrollBarEnabled(false)")
                        .addStatement("$view.setVerticalScrollBarEnabled(false)")
            }
        }
        return codes.build()
    }

    fun textAlignment(value: String): CodeBlock? {
        return code("\$T.TEXT_ALIGNMENT_\$L", cView, constant(value))
    }

    fun textDirection(value: String): CodeBlock? {
        return code("\$T.TEXT_ALIGNMENT_\$L", cView, constant(value))
    }
}