package com.example.timetable.data.network

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

suspend fun getTimeTableByDayOfWeek() = apiService.getTimeTableByDayOfWeek()

suspend fun sendGroupFilters(group: String, day_of_the_week: String, chosenDay: String) =
    apiService.sendGroupFilters(group,day_of_the_week,chosenDay)

suspend fun sendTeacherFilters(cafedra: String, teacherName: String, week: String, chosenDay: String) =
    apiService.sendTeacherFilters(cafedra, teacherName, week, chosenDay)

suspend fun sendAuditoryFilters(auditory: String,week: String, corpus: String, chosenDay: String) =
    apiService.sendAuditoryFilters(auditory,week, corpus, chosenDay)

suspend fun getFilterFacultyCourseWeek() = apiService.getFilterFacultyCourseWeek()

suspend fun getFilterFacultyTeacher() = apiService.getFilterFacultyTeacher()
suspend fun groupByFilter(faculty: String, course: String) =
    apiService.groupByFilter(faculty, course)

suspend fun teacherByFilter(faculty: String, cafedra: String) =
    apiService.teacherByFilter(faculty,cafedra)

suspend fun auditoryByFilter(corpus: String) =
    apiService.auditoryByFilter(corpus)

suspend fun getDaysOfWeek(week: String) = apiService.getDaysOfWeek(week)

suspend fun getFilterCafedraWeek(faculty: String) = apiService.getCafedraWeek(faculty)

suspend fun getAllFiltersAuditory() = apiService.getAllFiltersAuditory()

}
