package com.example.timetable.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weekly_calendar")
data class CurrentWeeklyCalendar (
    var daysOfWeekGroup: String,
    var numberOfMonthGroup: String,
)