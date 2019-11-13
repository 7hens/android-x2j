
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object Toolbar : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
        .add("android:buttonGravity") {
            // setCollapseContentDescription(int)
            todo()
        }
        .add("android:collapseContentDescription") {
            // setCollapseContentDescription(int)
            todo()
        }
        .add("android:collapseIcon") {
            // setCollapseIcon(int)
            todo()
        }
        .add("android:contentInsetEnd") {
            // setContentInsetsRelative(int,int)
            todo()
        }
        .add("android:contentInsetEndWithActions") {
            // setContentInsetEndWithActions(int)
            todo()
        }
        .add("android:contentInsetLeft") {
            // setContentInsetsAbsolute(int,int)
            todo()
        }
        .add("android:contentInsetRight") {
            // setContentInsetsAbsolute(int,int)
            todo()
        }
        .add("android:contentInsetStart") {
            // setContentInsetsRelative(int,int)
            todo()
        }
        .add("android:contentInsetStartWithNavigation") {
            // setContentInsetStartWithNavigation(int)
            todo()
        }
        .add("android:gravity") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("android:logo") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("android:logoDescription") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("android:maxButtonHeight") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("android:navigationContentDescription") {
            // setNavigationContentDescription(int)
            todo()
        }
        .add("android:navigationIcon") {
            // setNavigationIcon(int)
            todo()
        }
        .add("android:popupTheme") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("android:subtitle") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("android:subtitleTextAppearance") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("android:subtitleTextColor") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("android:title") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("android:titleMargin") {
            // setTitleMargin(int,int,int,int)
            todo()
        }
        .add("android:titleMarginBottom") {
            // setTitleMarginBottom(int)
            todo()
        }
        .add("android:titleMarginEnd") {
            // setTitleMarginEnd(int)
            todo()
        }
        .add("android:titleMarginStart") {
            // setTitleMarginStart(int)
            todo()
        }
        .add("android:titleMarginTop") {
            // setTitleMarginTop(int)
            todo()
        }
        .add("android:titleTextAppearance") {
            // 
            todo()
        }
        .add("android:titleTextColor") {
            // 
            todo()
        }
        .build()

    
    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
        .add("android:layout_gravity") {
            // 
            todo()
        }
    .build()
      
}
  