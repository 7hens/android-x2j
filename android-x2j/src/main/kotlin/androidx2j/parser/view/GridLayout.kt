package androidx2j.parser.view

import androidx2j.parser.AttrParser

/**
 * @author 7hens
 */

object GridLayout : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("alignmentMode") {
                // setAlignmentMode(int)
                todo()
            }
            .add("columnCount") {
                // setColumnCount(int)
                todo()
            }
            .add("columnOrderPreserved") {
                // setColumnOrderPreserved(boolean)
                todo()
            }
            .add("orientation") {
                // setOrientation(int)
                todo()
            }
            .add("rowCount") {
                // setRowCount(int)
                todo()
            }
            .add("rowOrderPreserved") {
                // setRowOrderPreserved(boolean)
                todo()
            }
            .add("useDefaultMargins") {
                // setUseDefaultMargins(boolean)
                todo()
            }
            .build()


    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
            .add("layout_column") {
                // setGravity(int)
                todo()
            }
            .add("layout_columnSpan") {
                // setGravity(int)
                todo()
            }
            .add("layout_columnWeight") {
                // setGravity(int)
                todo()
            }
            .add("layout_gravity") {
                // setGravity(int)
                todo()
            }
            .add("layout_row") {
                //
                todo()
            }
            .add("layout_rowSpan") {
                //
                todo()
            }
            .add("layout_rowWeight") {
                //
                todo()
            }
            .build()

}
