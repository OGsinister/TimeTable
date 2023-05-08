package com.example.timetable.data.model

import androidx.compose.ui.graphics.Color

data class Auditory(
    val lessonName: String,
    val classRoom: String,
    val lessonTypeColor: Color,
    val lessonType: String,
    val startSubject: String,
    val endSubject: String,
    val groups: List<String>,
)