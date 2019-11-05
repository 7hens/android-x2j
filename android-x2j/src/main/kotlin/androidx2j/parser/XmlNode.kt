package androidx2j.parser

import com.squareup.javapoet.ClassName
import org.xml.sax.Attributes

/**
 * @author 7hens
 */
data class XmlNode(
        val index: Int,
        val tagName: String,
        val attributes: Attributes,
        val parent: XmlNode?
) {
    val viewType = getViewTypeByTagName(tagName)
    val view = tagName.substringAfterLast(".").decapitalize() + index
    val layoutType = viewType.nestedClass("LayoutParams")
    val layout = "layoutParams$index"

    private fun getViewTypeByTagName(tagName: String): ClassName {
        return if (tagName.contains(".")) {
            ClassName.bestGuess(tagName)
        } else when (tagName) {
            "View", "ViewGroup", "ViewStub",
            "TextureView", "SurfaceView" -> ClassName.get("android.view", tagName)
            "WebView" -> ClassName.get("android.webkit", tagName)
            "fragment" -> ClassName.get("android.widget", "FrameLayout")
            else -> ClassName.get("android.widget", tagName)
        }
    }
}