package com.example.timetable.data.network

import com.example.timetable.data.model.AuditoryByFilter
import com.example.timetable.data.model.AuditoryFiltersFromAPI
import com.example.timetable.data.model.Group
import com.example.timetable.data.model.ResponseAuditoryFilters
import com.example.timetable.data.model.ResponseAuditoryTimeTable
import com.example.timetable.data.model.ResponseDaysOfWeek
import com.example.timetable.data.model.ResponseGroupFilters
import com.example.timetable.data.model.ResponseTeacherFaculty
import com.example.timetable.data.model.ResponseTeacherFilters
import com.example.timetable.data.model.ResponseTeacherTimeTable
import com.example.timetable.data.model.ResponseTimeTableGroup
import com.example.timetable.data.model.TeacherFiltersFromAPI
import com.example.timetable.data.model.TodosItem
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
@GET("todos")
suspend fun getTimeTableByDayOfWeek(): Response<List<TodosItem>>

@GET("scheduleForADay")
@Headers("Content-Type: application/json")
suspend fun sendGroupFilters(
    @Query("group") group: String,
    @Query("week") week: String?,
    @Query("day_of_the_week") day_of_the_week: String,
): Response<List<ResponseTimeTableGroup>>

@GET("scheduleTeacherForADay")
@Headers("Content-Type: application/json")
suspend fun sendTeacherFilters(
    @Query("cafedra") cafedra: String,
    @Query("teacherName") teacherName: String,
    @Query("week") week: String,
    @Query("day_of_the_week") chosenDay: String,
): Response<List<ResponseTeacherTimeTable>>

@GET("auditory")
@Headers("Content-Type: application/json")
suspend fun sendAuditoryFilters(
    @Query("auditory") auditory: String,
    @Query("week") week: String,
    @Query("corpus") corpus: String,
    @Query("chosenDay") chosenDay: String,
): Response<List<ResponseAuditoryTimeTable>>


@GET("getDaysOfWeek")
suspend fun getDaysOfWeek(
    @Query("week") week: String
): Response<List<ResponseDaysOfWeek>>


@GET("groupByFilter")
@Headers("Content-Type: application/json")
suspend fun groupByFilter(
    @Query("faculty") faculty: String,
    @Query("course") course: String,
): Response<List<Group>>

@GET("getFilterFacultyCourseWeek")
suspend fun getFilterFacultyCourseWeek(): Response<List<ResponseGroupFilters>>

// ---------------------------------------Teacher---------------------------------------------------
@GET("getFilterFacultyTeacher")
suspend fun getFilterFacultyTeacher(): Response<List<ResponseTeacherFaculty>>

@GET("getFiltersCafedraWeek")
suspend fun getCafedraWeek(
    @Query("faculty") faculty: String
): Response<List<ResponseTeacherFilters>>

@GET("getTeacherByFilter")
@Headers("Content-Type: application/json")
suspend fun teacherByFilter(
    @Query("faculty") faulty: String,
    @Query("cafedra") cafedra: String
): Response<List<TeacherFiltersFromAPI>>

// ---------------------------------------Auditory
@GET("getAllFiltersAuditory")
suspend fun getAllFiltersAuditory(): Response<List<ResponseAuditoryFilters>>

@GET("auditoryByFilter")
@Headers("Content-Type: application/json")
suspend fun auditoryByFilter(
    @Query("corpus") corpus: String
): Response<List<AuditoryFiltersFromAPI>>

}
