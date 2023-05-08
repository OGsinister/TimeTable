package com.example.timetable.ui.studentTimeTable

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
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.timetable.GetOutlinedTextField
import com.example.timetable.R
import com.example.timetable.data.model.CalendarTimeTable
import com.example.timetable.data.model.Filters
import com.example.timetable.localizedCurrentMonth
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.displayErrors.displayError
import com.example.timetable.ui.theme.*
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

var filters = Filters
var isCalendarTextVisible = mutableStateOf(false)

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun StudentScreen(navController: NavHostController, viewModel: MainViewModel) {

    val groupTimeTable = viewModel.groupFilters.observeAsState(listOf()).value
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        drawerShape = RoundedCornerShape(16.dp),
        sheetContent = {
            FiltersScreen(
                navController = navController,
                viewModel = viewModel,
                sheetState = sheetState
            )
    },
        sheetBackgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CurrentDateSection(
                navController = navController,
                sheetState = sheetState,
                viewModel = viewModel
            )
            WeeklyCalendarSection(viewModel = viewModel)

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                LazyColumn {
                    items(groupTimeTable) {
                        TimeTableListItem(responseTimeTableGroup = it, navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState", "RememberReturnType", "StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentDateSection(navController: NavController, sheetState: BottomSheetState, viewModel: MainViewModel) {
    val groups = viewModel.group.collectAsStateWithLifecycle().value

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
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 16.sp
                )
            )
            Text(
                text = groups,
                fontSize = 30.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface),
                modifier = Modifier
                    .height(60.dp),
                onClick = {
                    scope.launch {
                        if(sheetState.isExpanded) sheetState.collapse() else sheetState.expand()
                    }
                },
            ) {
                Text(text = "Изменить")
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyCalendarSection(viewModel: MainViewModel) {
    val weeks = viewModel.week.value
    val group = viewModel.group.value

    val calendarElements = viewModel.calendarElements

    var items by remember {
        mutableStateOf(
            (0..5).map {i ->
                CalendarTimeTable(
                    dayOfTheMonth = calendarElements,
                    dayOfTheWeek = calendarElements,
                    isSelected = i == 0
                )
            }
        )
    }
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Divider(
            color = MaterialTheme.colors.onSecondary
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

                            filters.currentDay = items[it].dayOfTheWeek.toString()

                            viewModel.viewModelScope.launch(Dispatchers.IO) {
                                viewModel.sendGroupFilters(
                                    group,
                                    weeks,
                                    //filters.group,
                                    //filters.week,
                                    calendarElements[it].day_of_the_week
                                )
                            }
                        },
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(isCalendarTextVisible.value){
                            Text(
                                text = calendarElements[it].date,
                                //text = mutableStateDate.value,
                                //text = items[it].dayOfTheMonth,
                                color = if(items[it].isSelected) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSecondary
                                //color = if (items[it].isSelected) calendarSelectedItemColor else SubjectsTextColor
                            )
                            Text(
                                text = calendarElements[it].day_of_the_week,
                                //text = items[it].dayOfTheWeek,x
                                color = if (items[it].isSelected) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSecondary
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                thickness = 2.dp,
                                color = if (items[it].isSelected) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSecondary
                            )
                        }
                    }
                }
            }
        }
        Divider(
            color = MaterialTheme.colors.onSecondary
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@Composable
fun FiltersScreen(navController: NavHostController, viewModel: MainViewModel, sheetState: BottomSheetState){

    val faculty = viewModel.faculty.collectAsState().value
    val courses = viewModel.course.collectAsState().value
    val weeks = viewModel.week.collectAsState().value

    var facultyList: List<String> = listOf("Не выбрано")
    var courseList: List<String> = listOf("Не выбрано")
    var groupList: List<String> = listOf("Не выбрано")
    var weekList: List<String> = listOf("Не выбрано")
    val scope = rememberCoroutineScope()

    val result = viewModel.groupFilters.observeAsState(listOf()).value
    val loadedFilters = viewModel.loadedGroupFilters.observeAsState(listOf()).value

    var loadedGroupFromFilters = viewModel.groupFromFilters.observeAsState(listOf()).value
    val loadedCalendar = viewModel.daysOfWeek.observeAsState(listOf()).value

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getFilterFacultyCourseWeek()
    }

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getDaysOfWeek(weeks)
    }

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.groupByFilter(faculty, courses)
    }

    loadedFilters.forEach{
        facultyList = it.faculty
        courseList = it.course
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
                            color = MaterialTheme.colors.surface,
                            fontSize = 15.sp
                        )

                    }
                    Column(){ // filters.faculty
                        filters.faculty = getOutlinedTextFieldFaculty(list = facultyList, viewModel = viewModel)
                        //filters.faculty = GetOutlinedTextField(facultyList, "Введите факультет", "Faculty", "Group")
                    }
                    Column(){
                        filters.course = getOutlinedTextFieldCourse(list = courseList, viewModel = viewModel)
                        //filters.course = GetOutlinedTextField(courseList,"Введите курс", "Course", "Group")
                    }

                    Column(){
                        filters.week = getOutlinedTextFieldWeek(list = weekList, viewModel = viewModel)
                        //filters.week = GetOutlinedTextField(weekList, "Введите неделю", "Week", "Group")
                    }

                    loadedGroupFromFilters.forEach { it ->
                        groupList = it.groupName
                    }

                    Column() {
                        filters.group = getOutlinedTextFieldGroup(list = groupList, viewModel = viewModel)
                        //filters.group = GetOutlinedTextField(groupList,"Введите группу", "Group", "Group")
                    }
                }
            }
            
            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Button(
                    onClick = {
                        viewModel._calendarElements.clear()
                        viewModel.viewModelScope.launch {
                            loadedCalendar.forEach { calendar ->
                                viewModel.addElementFromServer(calendar)
                            }
                        }

                        isCalendarTextVisible.value = true

                        scope.launch {
                            sheetState.collapse()
                        } //navController.navigate(Screens.STUDENT.route)
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface)
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}
