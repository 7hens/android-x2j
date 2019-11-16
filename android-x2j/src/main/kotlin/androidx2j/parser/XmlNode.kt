package androidx2j.parser

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName

/**
 * @author 7hens
 */
data class XmlNode(
        val tagName: String,
        val view: String,
        val viewType: TypeName,
        val layout: String,
        val layoutType: TypeName,
        val parent: XmlNode?
) {

    companion object {
        fun create(index: Int, tagName: String, parent: XmlNode?): XmlNode {
            val viewType = getViewTypeByTagName(tagName)
            val view = tagName.substringAfterLast(".").decapitalize() + index
            val layoutType = viewType.nestedClass("LayoutParams")
            val layout = "layoutParams$index"
            return XmlNode(tagName, view, viewType, layout, layoutType, parent)
        }

        val root: XmlNode by lazy {
            val viewType = ClassName.get("android.widget", "FrameLayout")
            val layoutType = viewType.nestedClass("LayoutParams")
            XmlNode("FrameLayout", "root", viewType, "", layoutType, null)
        }

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
}