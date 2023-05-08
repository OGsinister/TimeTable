package com.example.timetable.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LoadingScreenBackground = Color(0xFF72A5FD)

// SubjectInWeek Screen
val SubjectBackgroundColor = Color(0xFF292929)
val SubjectsTextColor = Color(0xFF8c8c8c)
val SubjectsNameSubjectColor = Color(0xFFf3f3f3)
val SubjectDayOfWeekColor = Color(0xFFFFFFFF)
val SubjectsCountInWeek = Color(0xFF8b8b8b)

// Student Screen
val backgroundColor = Color(0xFFfafafa)
val calendarSelectedItemColor = Color(0xFF49b583)

val textColorLight = Color(0xFFFFFFFF)
val textColorNight = Color(0xFF000000)

val ChangeButtonColorLight = Color(0XFF0000FF)
val ChangeButtonColorNight = Color(0xFFBB86FC)

// TimeTable
val lectureColor = Color(0xFF8086ff)
val examColor = Color(0xFFf02e44)
val armyColor = Color(0xFF4fc451)
val practiceColor = Color(0xFFFF9800)
var labColor = Color(0xFF8c03fc)


var currentSubjectColor = Color(0xFFFFFFFF)
var doneSubjectColor = Color(0xFF8b8b8b)
var doneTextSubjectColor = Color(0xFFC7C5C5)
var lineColor = Color(0xFF7f85fd)

// PRIMARY ДЛЯ BUTTON color
sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color
){
    object Night: ThemeColors(
        background = backgroundColor,
        surface = calendarSelectedItemColor,
        primary = ChangeButtonColorLight,
        text = textColorLight
    )
    object Light: ThemeColors(
        background = backgroundColor,
        surface = calendarSelectedItemColor,
        primary = ChangeButtonColorNight,
        text = textColorNight
    )
}


