
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object DatePicker : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("calendarTextColor") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("calendarViewShown") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("datePickerMode") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("dayOfWeekBackground") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("dayOfWeekTextAppearance") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("endYear") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("firstDayOfWeek") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("headerBackground") {
            // 
            todo()
        }
        .add("headerDayOfMonthTextAppearance") {
            // 
            todo()
        }
        .add("headerMonthTextAppearance") {
            // 
            todo()
        }
        .add("headerYearTextAppearance") {
            // 
            todo()
        }
        .add("maxDate") {
            // 
            todo()
        }
        .add("minDate") {
            // 
            todo()
        }
        .add("spinnersShown") {
            // 
            todo()
        }
        .add("startYear") {
            // 
            todo()
        }
        .add("yearListItemTextAppearance") {
            // 
            todo()
        }
        .add("yearListSelectorColor") {
            // 
            todo()
        }
        .build()

    
}
  