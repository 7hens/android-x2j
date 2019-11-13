
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object DatePicker : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("android:calendarTextColor") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:calendarViewShown") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:datePickerMode") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:dayOfWeekBackground") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:dayOfWeekTextAppearance") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:endYear") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:firstDayOfWeek") {
            // setFirstDayOfWeek(int)
            todo()
        }
        .add("android:headerBackground") {
            // 
            todo()
        }
        .add("android:headerDayOfMonthTextAppearance") {
            // 
            todo()
        }
        .add("android:headerMonthTextAppearance") {
            // 
            todo()
        }
        .add("android:headerYearTextAppearance") {
            // 
            todo()
        }
        .add("android:maxDate") {
            // 
            todo()
        }
        .add("android:minDate") {
            // 
            todo()
        }
        .add("android:spinnersShown") {
            // 
            todo()
        }
        .add("android:startYear") {
            // 
            todo()
        }
        .add("android:yearListItemTextAppearance") {
            // 
            todo()
        }
        .add("android:yearListSelectorColor") {
            // 
            todo()
        }
        .build()

    
}
  