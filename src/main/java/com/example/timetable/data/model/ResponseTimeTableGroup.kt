package com.example.timetable.data.model

import androidx.compose.ui.graphics.Color

data class ResponseTimeTableGroup(
    val lessonName: String,
    val classRoom: String,
    val lessonType: String,
    val teacherName: String,
    val startSubject: String,
    val endSubject: String,
)
