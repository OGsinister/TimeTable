package com.example.timetable.ui.theme

import android.annotation.SuppressLint
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
val textColorNight = Color(0xFF473568)

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
    val text: Color,
    val onSecondary: Color,
    val calendarSelectedItem: Color
){
    @SuppressLint("InvalidColorHexValue")
    object Night: ThemeColors(
        background = Color(0xFF000000),
        surface = Color(0xFF873CC7),
        primary = Color(0xFF6200ed),
        text = Color(0xFF4FB64C),
        onSecondary = Color(0xffFF473568),
        calendarSelectedItem = Color(0xFF49b583)
    )
    @SuppressLint("InvalidColorHexValue")
    object Light: ThemeColors(
        background = Color(0xFFfafafa),
        surface = Color(0xFF3700B3),
        primary = Color(0xFF6200ed),
        text = Color(0xFF72cfc4),
        onSecondary = Color(0xFF8c8c8c),
        calendarSelectedItem = Color(0xFF72A5FD)
    )
}


