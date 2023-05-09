package com.example.timetable.ui.teacherTimeTable

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetable.R
import com.example.timetable.data.model.CalendarTimeTable
import com.example.timetable.data.model.FiltersTeacher
import com.example.timetable.localizedCurrentMonth
import com.example.timetable.ui.theme.SubjectsTextColor
import com.example.timetable.ui.theme.calendarSelectedItemColor
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

var filtersTeacher = FiltersTeacher
val mutableStateCurrentTeacherName = mutableStateOf(filtersTeacher.teacher_name)
var isCalendarTeacherTextVisible = mutableStateOf(false)

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeacherScreen(viewModel: MainViewModel, navController: NavController, teacherName: String){

    val teacherTimeTable = viewModel.teacherTimeTable.observeAsState(listOf()).value

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        drawerShape = RoundedCornerShape(10),
        sheetContent = {
            TeacherFiltersScreen(navController = navController, viewModel = viewModel, sheetState = sheetState)
        },
        sheetBackgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CurrentDateSectionTeacher(
                navController = navController,
                viewModel = viewModel,
                sheetState = sheetState
            )
            WeeklyCalendarSectionTeacher(viewModel = viewModel)

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                LazyColumn {
                    items(teacherTimeTable) {
                        TeacherTimeTableListItem(responseTeacherTimeTable = it, navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState", "RememberReturnType")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentDateSectionTeacher(navController: NavController, viewModel: MainViewModel, sheetState: BottomSheetState) {
    val teacherName = viewModel.teacher.collectAsStateWithLifecycle().value
    val scope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${LocalDate.now().dayOfMonth} ${localizedCurrentMonth(localDate = LocalDate.now().month)}",
                style = TextStyle(
                    color = SubjectsTextColor,
                    fontSize = 16.sp
                )
            )
            Text(
                text = teacherName,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(60.dp),
                onClick = {
                    scope.launch {
                        if(sheetState.isCollapsed) sheetState.expand() else sheetState.collapse()
                    }
                },
            ) {
                Text(text = "Изменить")
            }
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyCalendarSectionTeacher(viewModel: MainViewModel) {
    val calendarElementsTeacher = viewModel.calendarElementsTeacher
    val facultyTeacher = viewModel.facultyTeacher.value
    val cafedraTeacher = viewModel.cafedraTeacher.value
    val weekTeacher = viewModel.weekTeacher.value
    val teacherName = viewModel.teacher.collectAsState().value

    var items by remember {
        mutableStateOf(
            (0..5).map {i ->
                CalendarTimeTable(
                    dayOfTheMonth = calendarElementsTeacher,
                    dayOfTheWeek = calendarElementsTeacher,
                    isSelected = i==0
                )
            }
        )
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Divider(
            color = Color.Black
        )
        LazyRow {
            items(items.size) {
                Card(
                    elevation = 0.dp,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(60.dp)
                        .clickable {
                            items = items.mapIndexed { j, item ->
                                item.copy(isSelected = it == j)
                            }
                            filtersTeacher.currentDay = items[it].dayOfTheWeek.toString()

                            viewModel.viewModelScope.launch(Dispatchers.IO) {
                                viewModel.sendTeacherFilters(
                                    cafedraTeacher,
                                    teacherName,
                                    weekTeacher,
                                    calendarElementsTeacher[it].day_of_the_week
                                )
                            }
                        },
                    backgroundColor = Color.White
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(isCalendarTeacherTextVisible.value){
                            Text(
                                text = calendarElementsTeacher[it].date,
                                color = if (items[it].isSelected) calendarSelectedItemColor else SubjectsTextColor
                            )
                            Text(
                                text = calendarElementsTeacher[it].day_of_the_week,
                                color = if (items[it].isSelected) calendarSelectedItemColor else Color.Black
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                thickness = 2.dp,
                                color = if (items[it].isSelected) calendarSelectedItemColor else Color.White
                            )
                        }
                    }
                }
            }
        }
        Divider(
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TeacherFiltersScreen(navController: NavController, viewModel: MainViewModel, sheetState: BottomSheetState){

    var context = LocalContext.current
    var facultyList: List<String> = listOf("Не выбрано")
    var cafedraList: List<String> = listOf("Не выбрано")
    var teacherList: List<String> = listOf("")
    var weekList: List<String> = listOf("Не выбрано")

    val facultyTeacher = viewModel.facultyTeacher.collectAsState().value
    val cafedraTeacher = viewModel.cafedraTeacher.collectAsState().value
    val weeksTeacher = viewModel.weekTeacher.collectAsState().value

    val scope = rememberCoroutineScope()

    val result = viewModel.teacherTimeTable.observeAsState(listOf()).value
    val loadedFilters = viewModel.loadedTeacherFilters.observeAsState(listOf()).value

    val loadedTeacherFromFilters = viewModel.daysOfWeek.observeAsState(listOf()).value
    val loadedFacultyTeacherFilter = viewModel.loadedFacultyFiltersTeacher.observeAsState().value
    val loadedTeacherNames = viewModel.teacherFromFilters.observeAsState().value

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getFilterFacultyTeacher()
    }

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getFiltersCafedraWeek(facultyTeacher)
    }

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getDaysOfWeek(weeksTeacher)
    }

    loadedFacultyTeacherFilter?.forEach {
        facultyList = it.faculty
    }

    loadedFilters.forEach{ it ->
        cafedraList = it.cafedra
        weekList = it.week
    }

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.teacherByFilter(facultyTeacher,cafedraTeacher)
    }

    loadedTeacherNames?.forEach {
        teacherList = it.teacherName
    }

    result.forEach {
        Log.d("checkDataTimeTable", "ID: ${it.startSubject}")
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
                            text = "Фильтры преподавателя",
                            color = Color.Black,
                            fontSize = 15.sp
                        )

                    }
                    Column(){
                        getOutlinedTextFieldFacultyTeacher(list = facultyList, viewModel = viewModel)
                        //filtersTeacher.faculty = GetOutlinedTextField(facultyList, "Введите факультет", "Faculty", "Teacher")
                    }
                    Column(){
                        getOutlinedTextFieldCafedra(list = cafedraList, viewModel = viewModel)
                        //filtersTeacher.cafedra = GetOutlinedTextField(cafedraList,"Введите кафедру", "Cafedra", "Teacher")
                    }
                    Column(){
                        getOutlinedTextFieldWeekTeacher(list = weekList, viewModel = viewModel)
                        //filtersTeacher.week = GetOutlinedTextField(weekList, "Введите неделю", "Week", "Teacher")
                    }

                    loadedTeacherFromFilters.forEach{ calendar ->
                        viewModel.addElementTeacherFromServer(calendar)
                    }

                    Column() {
                        getOutlinedTextFieldTeacher(list = teacherList, viewModel = viewModel)
                        //filtersTeacher.teacher_name = GetOutlinedTextField(teacherList,"Выберете преподавателя", "teacherName", "Teacher")
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Button(
                    onClick = {
                        viewModel._calendarElementsTeacher.clear()
                        viewModel.viewModelScope.launch {
                            loadedTeacherFromFilters.forEach { calendar ->
                                viewModel.addElementFromServer(calendar)
                            }
                        }
                        isCalendarTeacherTextVisible.value = true

                        scope.launch {
                            sheetState.collapse()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}