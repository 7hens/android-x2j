
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Toolbar : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
        .add("buttonGravity") {
            // setCollapseContentDescription(int)
            todo()
        }
        .add("collapseContentDescription") {
            // setCollapseContentDescription(int)
            todo()
        }
        .add("collapseIcon") {
            // setCollapseIcon(int)
            todo()
        }
        .add("contentInsetEnd") {
            // setContentInsetsRelative(int,int)
            todo()
        }
        .add("contentInsetEndWithActions") {
            // setContentInsetEndWithActions(int)
            todo()
        }
        .add("contentInsetLeft") {
            // setContentInsetsAbsolute(int,int)
            todo()
        }
        .add("contentInsetRight") {
            // setContentInsetsAbsolute(int,int)
            todo()
        }
        .add("contentInsetStart") {
            // setContentInsetsRelative(int,int)
            todo()
        }
        .add("contentInsetStartWithNavigation") {
            // setContentInsetStartWithNavigation(int)
            todo()
        }
        .add("gravity") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("logo") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("logoDescription") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("maxButtonHeight") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("navigationContentDescription") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("navigationIcon") {
            // setNavigationIcon(int)
            todo()
        }
        .add("popupTheme") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("subtitle") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("subtitleTextAppearance") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("subtitleTextColor") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("title") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("titleMargin") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("titleMarginBottom") {
            // setTitleMarginBottom(int)
            todo()
        }
        .add("titleMarginEnd") {
            // setTitleMarginEnd(int)
            todo()
        }
        .add("titleMarginStart") {
            // setTitleMarginStart(int)
            todo()
        }
        .add("titleMarginTop") {
            // setTitleMarginTop(int)
            todo()
        }
        .add("titleTextAppearance") {
            // 
            todo()
        }
        .add("titleTextColor") {
            // 
            todo()
        }
        .build()

    
    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
        .add("layout_gravity") {
            // 
            todo()
        }
    .build()
      
}
  