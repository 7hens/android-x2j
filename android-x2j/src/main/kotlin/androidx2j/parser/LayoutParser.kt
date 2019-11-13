package androidx2j.parser

import androidx2j.parser.view.*
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import java.util.*
import javax.xml.parsers.SAXParserFactory

/**
 * @author 7hens
 */
object LayoutParser {
    private val viewMap = mapOf(
            "AbsoluteLayout" to ViewGroup,
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
            "ViewGroup" to ViewGroup,
            "ViewSwitcher" to ViewAnimator,
            "WebView" to ViewGroup,
            "ZoomButton" to ImageView,
            "ZoomControls" to LinearLayout,
            "android.support.constraint.ConstraintLayout" to ConstraintLayout
    )

    fun parse(file: File): CodeBlock {
        val codes = CodeBlock.builder()
        val parser = SAXParserFactory.newInstance().newSAXParser()
        parser.parse(file, XmlHandler(codes))
        return codes.build()
    }

    private class XmlHandler(private val codes: CodeBlock.Builder) : DefaultHandler() {
        private val stack = Stack<XmlNode>()
        private var nodeIndex = 1

        init {
            val cResources = ClassName.get("android.content.res", "Resources")
            val cDisplayMetrics = ClassName.get("android.util", "DisplayMetrics")
            codes.addStatement("final \$T resources = context.getResources()", cResources)
                    .addStatement("final \$T displayMetrics = resources.getDisplayMetrics()", cDisplayMetrics)
        }

        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
            super.startElement(uri, localName, qName, attributes)
            val node = XmlNode(nodeIndex++, qName, attributes, stack.firstOrNull())
            stack.push(node)

            line()
            line("\$T \$L = new \$T(context)", node.viewType, node.view, node.viewType)

            val parentType = node.parent?.viewType ?: ClassName.get("android.widget", "FrameLayout")
            val parentLayoutType = parentType.nestedClass("LayoutParams")
            line("\$T \$L = new \$T(0, 0)", parentLayoutType, node.layout, parentLayoutType)

            codes.add(parse(node))
        }

        override fun endElement(uri: String, localName: String, qName: String) {
            super.endElement(uri, localName, qName)
            val node = stack.pop()
            if (stack.isEmpty()) {
                codes.add("\n")
                        .addStatement("return ${node.view}")
            } else {
                val parent = stack.peek()
                codes.addStatement("\$L.setLayoutParams(\$L)", node.view, node.layout)
                        .addStatement("${parent.view}.addView(${node.view})")
            }
        }

        private fun line(format: String = "", vararg args: Any?) {
            if (format.isEmpty()) {
                codes.add("\n")
            } else {
                codes.addStatement(format, *args)
            }
        }

        private fun parse(node: XmlNode): CodeBlock? {
            val parser = getParser(node)
            val attributes = node.attributes
            val codes = CodeBlock.builder()

            val style = attributes.getValue("style")
            if (!style.isNullOrEmpty()) {
                codes.add(StyleParser.parse(parser, node, getStyleName(style)))
            }

            for (i in 0 until attributes.length) {
                val name = attributes.getQName(i)
                val value = attributes.getValue(i)
                val code = parser.parse(node, name, value)
                if (code != null) {
                    codes.add(code)
                } else if (!name.startsWith("tools:") && !name.startsWith("xmlns:") && name != "style") {
                    codes.add("// $name=\"$value\"\n")
                }
            }
            parser.parse(node, AttrParser.END, "")?.let { codes.add(it) }
            return codes.build()
        }

        private fun getParser(node: XmlNode): AttrParser {
            val view = viewMap[node.tagName] ?: View
            val viewParent = node.parent?.let { viewMap[it.tagName] as? IView.Parent } ?: ViewGroup
            return view.parser(viewParent)
        }

        private fun getStyleName(value: String): String {
            return when {
                value.startsWith("@style") -> value.substring(value.lastIndexOf("/") + 1)
                else -> ""
            }
        }
    }
}