package com.example.timetable.data.model

data class ResponseTeacherTimeTable(
    val lessonName: String,
    val classRoom: String,
    val lessonTypeColor: String,
    val lessonType: String,
    val groupName: String,
    val startSubject: String,
    val endSubject: String,
)