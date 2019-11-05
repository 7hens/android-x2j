package androidx2j.parser.view

import androidx2j.parser.AttrParser

/**
 * @author 7hens
 */
object RelativeLayout : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .build()

    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
            .build()
}