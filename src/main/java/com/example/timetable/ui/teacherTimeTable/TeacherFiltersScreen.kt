package com.example.timetable.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.timetable.GetOutlinedTextField
import com.example.timetable.R
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.displayErrors.displayError
import com.example.timetable.ui.studentTimeTable.filters
import com.example.timetable.ui.teacherTimeTable.filtersTeacher
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TeacherFiltersScreen(navController: NavHostController, viewModel: MainViewModel){
    var facultyList: List<String> = listOf("Не выбрано")
    var cafedraList: List<String> = listOf("Не выбрано")
    var teacherList: List<String> = listOf("Не выбрано")
    var weekList: List<String> = listOf("Не выбрано")

    var context = LocalContext.current
    val filtersMessage = stringResource(id = R.string.ErrorEmptyFilters)

    val loadedFilters = viewModel.loadedTeacherFilters.observeAsState(listOf()).value

   */
/* viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getAllFiltersTeacher()
    }*//*


    loadedFilters.forEach{ it ->
        facultyList = it.faculty
        cafedraList = it.cafedra
        teacherList = it.teacherName
        weekList = it.week
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ){
            Column(){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column() {
                        Text(
                            text = "Фильтры группы",
                            color = Color.Black,
                            fontSize = 15.sp
                        )

                    }
                    Column(){
                        filters.faculty = GetOutlinedTextField(facultyList, "Введите факультет", "Faculty", "Group")
                    }
                    Column(){
                        filters.course = GetOutlinedTextField(courseList,"Введите курс", "Course", "Group")
                    }

                    Column(){
                        filters.week = GetOutlinedTextField(weekList, "Введите неделю", "Week", "Group")
                    }
                    if(filters.faculty != "" && filters.course != "")
                    {
                        viewModel.groupByFilter(filters.faculty, filters.course)
                        loadedGroupFromFilters.forEach{ it ->
                            groupList = it.groupName
                        }

                        Column() {
                            filters.group = GetOutlinedTextField(groupList,"Введите группу", "Group", "Group")
                        }
                    }else{
                        Column(){
                            displayError(context,filtersMessage)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Button(
                    onClick = {
                        if(filters.group != "" && filters.faculty != "" && filters.course != "" && filters.week != ""){

                            viewModel._calendarElements.clear()

                            viewModel.viewModelScope.launch {
                                loadedCalendar.forEach { calendar ->
                                    Log.d("FFFFF",calendar.day_of_the_week.toString())
                                    Log.d("KKKKK",calendar.date)

                                    viewModel.addElementFromServer(calendar)
                                }
                            }
                            Log.d("SIZE",loadedCalendar.size.toString())

                            mutableStateCurrentGroupName.value = filters.group
                            isCalendarTextVisible.value = true
                            scope.launch {
                                sheetState.collapse()
                            }

                            //navController.navigate(Screens.STUDENT.route)
                        }else{
                            displayError(context, filtersMessage)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}*/
