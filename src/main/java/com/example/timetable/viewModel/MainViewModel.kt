package com.example.timetable.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.timetable.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    // Group Filters Save Data
    val faculty = savedStateHandle.getStateFlow("faculty", "ФИРТ")
    val course = savedStateHandle.getStateFlow("course","3")
    val week = savedStateHandle.getStateFlow("week","3")
    val group = savedStateHandle.getStateFlow("group","ПРО-329")
    val currentDay = savedStateHandle.getStateFlow("day","Пн")
    fun onFacultyChanged(NewValue: String){
        savedStateHandle["faculty"] = NewValue
    }

    fun onCourseChanged(NewValue: String){
        savedStateHandle["course"] = NewValue
    }

    fun onWeeksChanged(NewValue: String){
        savedStateHandle["week"] = NewValue
    }

    fun onGroupChanged(NewValue: String){
        savedStateHandle["group"] = NewValue
    }

    fun onDayChanged(NewValue: String){
        savedStateHandle["day"] = NewValue
    }

    val currentLang = androidx.compose.ui.text.intl.Locale.current.language
    private fun getNameByLocation(locale: String): String{
        return if(locale == "en") "Don't chosen" else "Не выбран"
    }

    // Teacher Filters Save Data
    val facultyTeacher = savedStateHandle.getStateFlow("facultyTeacher","ФИРТ")
    val cafedraTeacher = savedStateHandle.getStateFlow("cafedraTeacher","ВМиК")
    val weekTeacher = savedStateHandle.getStateFlow("weekTeacher","3")
    val teacher = savedStateHandle.getStateFlow("teacher",getNameByLocation(currentLang))
    val currentDayTeacher = savedStateHandle.getStateFlow("dayTeacher","Пн")
    fun onFacultyChangedTeacher(NewValue: String){
        savedStateHandle["facultyTeacher"] = NewValue
    }

    fun onCafedraChangedTeacher(NewValue: String){
        savedStateHandle["cafedraTeacher"] = NewValue
    }

    fun onWeeksChangedTeacher(NewValue: String){
        savedStateHandle["weekTeacher"] = NewValue
    }

    fun onTeacherNameChangedTeacher(NewValue: String){
        savedStateHandle["teacher"] = NewValue
    }

    fun onDayChangedTeacher(NewValue: String){
        savedStateHandle["dayTeacher"] = NewValue
    }

    // Auditory Filters Save Data
    val auditory = savedStateHandle.getStateFlow("auditory",getNameByLocation(currentLang))
    val corpusAuditory = savedStateHandle.getStateFlow("corpusAuditory","1")
    val weekAuditory = savedStateHandle.getStateFlow("weekAuditory","3")
    val currentDayAuditory = savedStateHandle.getStateFlow("dayAuditory","Пн")
    fun onAuditoryChanged(NewValue: String){
        savedStateHandle["auditory"] = NewValue
    }

    fun onCorpusChangedAuditory(NewValue: String){
        savedStateHandle["corpusAuditory"] = NewValue
    }

    fun onWeekChangedAuditory(NewValue: String){
        savedStateHandle["weekAuditory"] = NewValue
    }

    fun onDayChangedAuditory(NewValue: String){
        savedStateHandle["dayAuditory"] = NewValue
    }

    val _calendarElements = mutableStateListOf<ResponseDaysOfWeek>()
    val calendarElements: List<ResponseDaysOfWeek> = _calendarElements

    fun addElementFromServer(dayOfWeek: ResponseDaysOfWeek){
        _calendarElements.add(dayOfWeek)
        Log.d("titan", "Added elements: ${_calendarElements.last()}")
    }

    fun addElementFromServerAuditory(dayOfWeek: ResponseDaysOfWeek){
        _calendarElementsAuditory.add(dayOfWeek)
    }

    // Teacher calendar area
    val _calendarElementsTeacher = mutableStateListOf<ResponseDaysOfWeek>()
    val calendarElementsTeacher: List<ResponseDaysOfWeek> = _calendarElementsTeacher

    fun addElementTeacherFromServer(dayOfWeek: ResponseDaysOfWeek){
        _calendarElementsTeacher.add(dayOfWeek)
        Log.d("titan", "Added elements: ${_calendarElementsTeacher.last()}")
    }

    // Auditory area
    val _calendarElementsAuditory = mutableStateListOf<ResponseDaysOfWeek>()
    val calendarElementsAuditory: List<ResponseDaysOfWeek> = _calendarElementsAuditory

    fun addElementAuditoryFromServer(dayOfWeek: ResponseDaysOfWeek){
        _calendarElementsAuditory.add(dayOfWeek)
        Log.d("titan", "Added elements: ${_calendarElementsAuditory.last()}")
    }

    private val _calendar = MutableLiveData<List<ResponseDaysOfWeek>>()
    val calendar: MutableLiveData<List<ResponseDaysOfWeek>>
        get() = _calendar

    private val _groupFromFilters = MutableLiveData<List<Group>>()
    val groupFromFilters: MutableLiveData<List<Group>>
        get() = _groupFromFilters

    private val _teacherFromFilters = MutableLiveData<List<TeacherFiltersFromAPI>>()
    val teacherFromFilters: MutableLiveData<List<TeacherFiltersFromAPI>>
        get() = _teacherFromFilters

    private val _auditoryFromFilters = MutableLiveData<List<AuditoryFiltersFromAPI>>()
    val auditoryFromFilters: MutableLiveData<List<AuditoryFiltersFromAPI>>
        get() = _auditoryFromFilters

    private val _daysOfWeek = MutableLiveData<List<ResponseDaysOfWeek>>()
    val daysOfWeek: MutableLiveData<List<ResponseDaysOfWeek>>
        get() = _daysOfWeek

    private val _todos = MutableLiveData<List<TodosItem>>()
    val todos: MutableLiveData<List<TodosItem>>
        get() = _todos

    private val _groupFilters = MutableLiveData<List<ResponseTimeTableGroup>>()
    val groupFilters: MutableLiveData<List<ResponseTimeTableGroup>>
        get() = _groupFilters

    private val _teacherTimeTable = MutableLiveData<List<ResponseTeacherTimeTable>>()
    val teacherTimeTable: MutableLiveData<List<ResponseTeacherTimeTable>>
        get() = _teacherTimeTable

    private val _auditoryTimeTable = MutableLiveData<List<ResponseAuditoryTimeTable>>()
    val auditoryTimeTable: MutableLiveData<List<ResponseAuditoryTimeTable>>
        get() = _auditoryTimeTable

    private val _loadedGroupFilters = MutableLiveData<List<ResponseGroupFilters>>()
    val loadedGroupFilters: MutableLiveData<List<ResponseGroupFilters>>
        get() = _loadedGroupFilters

    private val _loadedTeacherFilters = MutableLiveData<List<ResponseTeacherFilters>>()
    val loadedTeacherFilters: MutableLiveData<List<ResponseTeacherFilters>>
        get() = _loadedTeacherFilters

    val _loadedFacultyFiltersTeacher = MutableLiveData<List<ResponseTeacherFaculty>>()
    val loadedFacultyFiltersTeacher: MutableLiveData<List<ResponseTeacherFaculty>>
        get() = _loadedFacultyFiltersTeacher

    private val _loadedAuditoryFilters = MutableLiveData<List<ResponseAuditoryFilters>>()
    val loadedAuditoryFilters: MutableLiveData<List<ResponseAuditoryFilters>>
        get() = _loadedAuditoryFilters

    fun getTimeTableByDayOfWeek(){
        viewModelScope.launch{
            repository.getTimeTableByDayOfWeek().let {
                if(it.isSuccessful){
                    try{
                        todos.postValue(it.body())
                    }catch (e: IOException){
                    }
                }else{
                    Log.d("checkData", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun sendGroupFilters(group: String, week: String, chosenDay: String){
        viewModelScope.launch {
            repository.sendGroupFilters(group, week, chosenDay).let {
                if(it.isSuccessful){
                    try{
                        groupFilters.postValue(it.body())
                        Log.d("GROUP", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                    }
                }else{
                    Log.d("checkDataGroup", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun getDaysOfWeek(week: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDaysOfWeek(week).let {
                if(it.isSuccessful){
                    try {
                        daysOfWeek.postValue(it.body())
                        Log.d("DaysOfWeek", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                        Log.d("Error", "Failed to load product: ${e.message.toString()}")
                    }
                }else{
                    Log.d("checkData", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun sendTeacherFilters(cafedra: String, teacherName: String, week: String, chosenDay: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendTeacherFilters(cafedra, teacherName, week, chosenDay).let {
                if(it.isSuccessful){
                    try {
                        teacherTimeTable.postValue(it.body())
                        Log.d("checkssssTimeTable", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                        Log.d("Error", "Failed to load product: ${e.message.toString()}")
                    }
                }else{
                    Log.d("checkDataTimeTable", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun groupByFilter(faculty: String, course: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.groupByFilter(faculty,course).let {
                if(it.isSuccessful){
                    try {
                        groupFromFilters.postValue(it.body())
                        Log.d("checkssss", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                        Log.d("Error GROUP", "Failed to load product: ${e.message.toString()}")
                    }
                }else{
                    Log.d("checkData GROUP", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun teacherByFilter(faculty: String, cafedra: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.teacherByFilter(faculty,cafedra).let {
                if(it.isSuccessful){
                    try {
                        teacherFromFilters.postValue(it.body())
                        Log.d("checkssss", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                        Log.d("Error", "Failed to load product: ${e.message.toString()}")
                    }
                }else{
                    Log.d("checkData", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun auditoryByFilter(corpus: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.auditoryByFilter(corpus).let {
                if(it.isSuccessful){
                    try {
                        auditoryFromFilters.postValue(it.body())
                        Log.d("checkssss", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                        Log.d("Error", "Failed to load product: ${e.message.toString()}")
                    }
                }else{
                    Log.d("checkData", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun sendAuditoryFilters(auditory: String, week: String, chosenDay: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendAuditoryFilters(auditory,week, chosenDay).let {
                if(it.isSuccessful){
                    try {
                        auditoryTimeTable.postValue(it.body())
                        Log.d("TimeTableAUDDDDi", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){
                        Log.d("Error", "Failed to load product: ${e.message.toString()}")
                    }
                }else{
                    Log.d("checkData", "Failed to load product: ${it.errorBody()}")
                }
            }
        }
    }

    fun getFilterFacultyCourseWeek(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFilterFacultyCourseWeek().let{
                if(it.isSuccessful){
                    try {
                        loadedGroupFilters.postValue(it.body())
                    }catch (e: IOException){}
                }else{
                    Log.d("ffffff", "Failed to load ...: ${it.errorBody()}")
                }
            }
        }
    }

    fun getFilterFacultyTeacher(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFilterFacultyTeacher().let{
                if(it.isSuccessful){
                    try {
                        loadedFacultyFiltersTeacher.postValue(it.body())
                    }catch (e: IOException){}
                }else{
                    Log.d("ffffff", "Failed to load ...: ${it.errorBody()}")
                }
            }
        }
    }

    fun getFiltersCafedraWeek(faculty: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFilterCafedraWeek(faculty).let {
                if(it.isSuccessful){
                    try {
                        loadedTeacherFilters.postValue(it.body())
                    }catch (e: IOException){}
                }else{
                    Log.d("ffffff", "Failed to load ...: ${it.errorBody()}")
                }
            }
        }
    }

    fun getFiltersCorpusWeek(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFiltersAuditory().let {
                if(it.isSuccessful){
                    try {
                        loadedAuditoryFilters.postValue(it.body())
                        Log.d("checkASS", "Load ->: ${it.body().toString()}")
                    }catch (e: IOException){}
                }else{
                    Log.d("ffffffAudit", "Failed to load ...: ${it.errorBody()}")
                }
            }
        }
    }
}