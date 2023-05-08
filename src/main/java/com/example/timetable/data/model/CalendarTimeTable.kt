package com.example.timetable.data.model

import android.service.autofill.OnClickAction
import okhttp3.Interceptor.Companion.invoke

data class CalendarTimeTable(
    var dayOfTheMonth: List<ResponseDaysOfWeek>,
    var dayOfTheWeek: List<ResponseDaysOfWeek>,
    var isSelected: Boolean = false,
)