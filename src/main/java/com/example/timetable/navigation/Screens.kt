package com.example.timetable.navigation

import androidx.compose.ui.res.stringResource
import com.example.timetable.R

sealed class Screens(val route: String){
    object STUDENT_FILTERS: Screens("Фильтр_Студент")
    object TEACHER_FILTERS: Screens("Фильтр-Учитель")
    object AUDITORY_FILTERS: Screens("Фильтр-Аудитория")

    object STUDENT: Screens("Студент")
    object TEACHER: Screens("Учитель")
    object AUDITORY: Screens("Аудитория")
    object NO_INTERNET: Screens("Нет интернета")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {args ->
                append("/$args")
            }
        }
    }
}
