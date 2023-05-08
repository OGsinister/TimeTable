package com.example.timetable.data.model

data class ResponseAuditoryTimeTable(
    val lessonName: String,
    val group: String,
    val lessonTypeColor: String,
    val lessonType: String,
    val teacherName: String,
    val startSubject: String,
    val endSubject: String,
)