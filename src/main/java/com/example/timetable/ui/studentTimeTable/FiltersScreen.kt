/*
package com.example.timetable.ui
*/
/*

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.timetable.GetOutlinedTextField
import com.example.timetable.R
import com.example.timetable.data.model.Filters
import com.example.timetable.data.model.ResponseGroupFilters
import com.example.timetable.data.model.ResponseTimeTableGroup
import com.example.timetable.data.model.TimeTable
import com.example.timetable.getSheetState
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.displayErrors.displayError
import com.example.timetable.ui.studentTimeTable.currentGroupName
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

var filters = Filters

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FiltersScreen(navController: NavHostController, viewModel: MainViewModel, sheetState: BottomSheetState){
    val context = LocalContext.current
    val filtersMessage = stringResource(id = R.string.ErrorEmptyFilters)
    var facultyList: List<String> = listOf("Не выбрано")
    var courseList: List<String> = listOf("Не выбрано")
    var groupList: List<String> = listOf("Не выбрано")
    var weekList: List<String> = listOf("Не выбрано")

    val result = viewModel.groupFilters.observeAsState(listOf()).value
    val loadedFilters = viewModel.loadedGroupFilters.observeAsState(listOf()).value

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getAllFiltersGroup()
    }

    loadedFilters.forEach{ it ->
        facultyList = it.faculty
        courseList = it.course
        groupList = it.group
        weekList = it.week
    }

    result.forEach {
        Log.d("checkData", "ID: ${it.startSubject}")
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
                        filters.faculty = GetOutlinedTextField(facultyList, "Введите факультет", "Faculty")
                    }
                    Column(){
                        filters.course = GetOutlinedTextField(courseList,"Введите курс", "Course")
                    }
                    Column() {
                        filters.group = GetOutlinedTextField(groupList,"Введите группу", "Group")
                    }
                    Column(){
                        filters.week = GetOutlinedTextField(weekList, "Введите неделю", "Week")
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Button(
                    onClick = {
                        // Send data to server
                        */
/*val request = filters.faculty?.let {
                            filters.course?.let { it1 ->
                                filters.group?.let { it2 ->
                                    RequestGroupFilters(
                                        faculty = it,
                                        course = it1,
                                        group = it2,
                                        week = 12
                                    )
                                }
                            }
                        }*//*

                        if(filters.group != "" && filters.faculty != "" && filters.course != "" && filters.week != ""){
                            // viewModel.sendGroupFilters(filters.faculty, filters.course, filters.group, filters.week)
                            currentGroupName.group = filters.group
                            //navController.navigate(Screens.STUDENT.withArgs(filters.group.toString()))
                        }else{
                            displayError(context, filtersMessage)
                        }},
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}
*/
