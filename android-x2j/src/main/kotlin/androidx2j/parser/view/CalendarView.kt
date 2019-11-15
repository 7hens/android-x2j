
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object CalendarView : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("dateTextAppearance") {
            // setDateTextAppearance(int)
            todo()
        }
        .add("firstDayOfWeek") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("focusedMonthDateColor") {
            // setFocusedMonthDateColor(int)
            todo()
        }
        .add("maxDate") {
            // setMaxDate(long)
            todo()
        }
        .add("minDate") {
            // setMinDate(long)
            todo()
        }
        .add("selectedDateVerticalBar") {
            // setSelectedDateVerticalBar(Drawable)
            todo()
        }
        .add("selectedWeekBackgroundColor") {
            // setSelectedWeekBackgroundColor(int)
            todo()
        }
        .add("showWeekNumber") {
            // setShowWeekNumber(boolean)
            todo()
        }
        .add("shownWeekCount") {
            // setShownWeekCount(int)
            todo()
        }
        .add("unfocusedMonthDateColor") {
            // setUnfocusedMonthDateColor(int)
            todo()
        }
        .add("weekDayTextAppearance") {
            // setWeekDayTextAppearance(int)
            todo()
        }
        .add("weekNumberColor") {
            // setWeekNumberColor(int)
            todo()
        }
        .add("weekSeparatorLineColor") {
            // setWeekSeparatorLineColor(int)
            todo()
        }
        .build()

    
}
  