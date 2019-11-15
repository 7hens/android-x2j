package androidx2j.parser

import androidx2j.parser.view.*

/**
 * @author 7hens
 */
object AttrParserFactory {
    private val viewMap = mapOf(
            "AbsoluteLayout" to ViewGroup,
            "AdapterViewFlipper" to AdapterViewFlipper,
            "ActionMenuView" to LinearLayout,
            "AnalogClock" to AnalogClock,
            "AppWidgetHostView" to FrameLayout,
            "AutoCompleteTextView" to AutoCompleteTextView,
            "Button" to TextView,
            "CalendarView" to CalendarView,
            "CheckBox" to CompoundButton,
            "CheckedTextView" to CheckedTextView,
            "Chronometer" to Chronometer,
            "CompoundButton" to CompoundButton,
            "DatePicker" to DatePicker,
            "DialerFilter" to RelativeLayout,
            "DigitalClock" to TextView,
            "EditText" to TextView,
            "ExpandableListView" to ExpandableListView,
            "ExtractEditText" to TextView,
            "FragmentBreadCrumbs" to ViewGroup,
            "FrameLayout" to FrameLayout,
            "Gallery" to Gallery,
            "GestureOverlayView" to GestureOverlayView,
            "GridLayout" to GridLayout,
            "GridView" to GridView,
            "HorizontalScrollView" to HorizontalScrollView,
            "ImageButton" to ImageView,
            "ImageSwitcher" to ViewAnimator,
            "ImageView" to ImageView,
            "KeyboardView" to KeyboardView,
            "LinearLayout" to LinearLayout,
            "ListView" to ListView,
            "MediaController" to FrameLayout,
            "MultiAutoCompleteTextView" to AutoCompleteTextView,
            "NumberPicker" to LinearLayout,
            "ProgressBar" to ProgressBar,
            "QuickContactBadge" to ImageView,
            "RadioButton" to CompoundButton,
            "RadioGroup" to RadioGroup,
            "RatingBar" to RatingBar,
            "RelativeLayout" to RelativeLayout,
            "ScrollView" to ScrollView,
            "SearchView" to SearchView,
            "SeekBar" to SeekBar,
            "SlidingDrawer" to SlidingDrawer,
            "Spinner" to Spinner,
            "StackView" to AdapterViewAnimator,
            "Switch" to Switch,
            "TabHost" to FrameLayout,
            "TableLayout" to TableLayout,
            "TableRow" to TableRow,
            "TabWidget" to TabWidget,
            "TextClock" to TextClock,
            "TextSwitcher" to ViewAnimator,
            "TextView" to TextView,
            "TimePicker" to TimePicker,
            "ToggleButton" to ToggleButton,
            "Toolbar" to Toolbar,
            "TvView" to ViewGroup,
            "TwoLineListItem" to TwoLineListItem,
            "View" to View,
            "ViewAnimator" to ViewAnimator,
            "ViewFlipper" to ViewFlipper,
            "ViewGroup" to ViewGroup,
            "ViewStub" to ViewStub,
            "ViewSwitcher" to ViewAnimator,
            "WebView" to ViewGroup,
            "ZoomButton" to ImageView,
            "ZoomControls" to LinearLayout,
            "android.support.constraint.ConstraintLayout" to ConstraintLayout
    )

    fun getParser(node: XmlNode): AttrParser {
        val view = viewMap[node.tagName] ?: View
        val viewParent = node.parent?.let { viewMap[it.tagName] as? IView.Parent } ?: ViewGroup
        return view.getParser(viewParent)
    }
}