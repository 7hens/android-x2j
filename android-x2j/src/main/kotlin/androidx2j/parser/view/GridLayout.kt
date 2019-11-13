package androidx2j.parser.view

import androidx2j.parser.AttrParser

/**
 * @author 7hens
 */

object GridLayout : IView, IView.Parent {
    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("android:alignmentMode") {
                // setAlignmentMode(int)
                todo()
            }
            .add("android:columnCount") {
                // setColumnCount(int)
                todo()
            }
            .add("android:columnOrderPreserved") {
                // setColumnOrderPreserved(boolean)
                todo()
            }
            .add("android:orientation") {
                // setOrientation(int)
                todo()
            }
            .add("android:rowCount") {
                // setRowCount(int)
                todo()
            }
            .add("android:rowOrderPreserved") {
                // setRowOrderPreserved(boolean)
                todo()
            }
            .add("android:useDefaultMargins") {
                // setUseDefaultMargins(boolean)
                todo()
            }
            .build()


    override val childParser = ViewGroup.childParser + AttrParser.androidBuilder()
            .add("android:layout_column") {
                // setGravity(int)
                todo()
            }
            .add("android:layout_columnSpan") {
                // setGravity(int)
                todo()
            }
            .add("android:layout_columnWeight") {
                // setGravity(int)
                todo()
            }
            .add("android:layout_gravity") {
                // setGravity(int)
                todo()
            }
            .add("android:layout_row") {
                //
                todo()
            }
            .add("android:layout_rowSpan") {
                //
                todo()
            }
            .add("android:layout_rowWeight") {
                //
                todo()
            }
            .build()

}
