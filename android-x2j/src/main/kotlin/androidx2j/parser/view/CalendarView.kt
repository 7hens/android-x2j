
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object CalendarView : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("android:dateTextAppearance") {
            // setDateTextAppearance(int)
            todo()
        }
        .add("android:firstDayOfWeek") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:focusedMonthDateColor") {
            // setFocusedMonthDateColor(int)
            todo()
        }
        .add("android:maxDate") {
            // setMaxDate(long)
            todo()
        }
        .add("android:minDate") {
            // setMinDate(long)
            todo()
        }
        .add("android:selectedDateVerticalBar") {
            // setSelectedDateVerticalBar(Drawable)
            todo()
        }
        .add("android:selectedWeekBackgroundColor") {
            // setSelectedWeekBackgroundColor(int)
            todo()
        }
        .add("android:showWeekNumber") {
            // setShowWeekNumber(boolean)
            todo()
        }
        .add("android:shownWeekCount") {
            // setShownWeekCount(int)
            todo()
        }
        .add("android:unfocusedMonthDateColor") {
            // setUnfocusedMonthDateColor(int)
            todo()
        }
        .add("android:weekDayTextAppearance") {
            // setWeekDayTextAppearance(int)
            todo()
        }
        .add("android:weekNumberColor") {
            // setWeekNumberColor(int)
            todo()
        }
        .add("android:weekSeparatorLineColor") {
            // setWeekSeparatorLineColor(int)
            todo()
        }
        .build()

    
}
  