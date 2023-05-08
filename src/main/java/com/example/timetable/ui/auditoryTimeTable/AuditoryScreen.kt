package com.example.timetable.ui

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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.timetable.GetOutlinedTextField
import com.example.timetable.R
import com.example.timetable.data.model.CalendarTimeTable
import com.example.timetable.data.model.FiltersAuditory
import com.example.timetable.localizedCurrentMonth
import com.example.timetable.ui.auditoryTimeTable.AuditoryTimeTableListItem
import com.example.timetable.ui.displayErrors.displayError
import com.example.timetable.ui.studentTimeTable.filters
import com.example.timetable.ui.studentTimeTable.isCalendarTextVisible
import com.example.timetable.ui.theme.SubjectsTextColor
import com.example.timetable.ui.theme.calendarSelectedItemColor
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

var filtersAuditory = FiltersAuditory
var isCalendarAuditoryTextVisible = mutableStateOf(false)
val mutableStateCurrentAuditory = mutableStateOf(filtersAuditory.auditory)

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuditoryScreen(navController: NavHostController, viewModel: MainViewModel, auditoryName: String){

    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getFiltersCorpusWeek()
    }

    var auditoryTimeTable = viewModel.auditoryTimeTable.observeAsState(listOf()).value

    /*viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.sendAuditoryFilters(
            filtersAuditory.corpus,
            filtersAuditory.week,
            filtersAuditory.currentDay
        )
    }*/

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        drawerShape = RoundedCornerShape(10),
        sheetContent = {
            AuditoryFiltersScreen(navController = navController, viewModel = viewModel, sheetState = sheetState)
        },
        sheetBackgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CurrentDateSectionAuditory(
                navController = navController,
                viewModel = viewModel,
                sheetState = sheetState
            )
            WeeklyCalendarSectionAuditory(viewModel = viewModel)

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                LazyColumn {
                    items(auditoryTimeTable) {
                        AuditoryTimeTableListItem(responseAuditoryTimeTable = it, navController)
                    }
                }
            }
        }
    }
}

/*
@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSection(navController: NavHostController, viewModel: MainViewModel, auditoryName: String) {
    //var selectedItem by remember { mutableStateOf(auditoryName) }
    var selectedItem by remember { mutableStateOf(if(auditoryName == "{auditoryName}") "Не выбрано" else auditoryName) }

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
                text = selectedItem,
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
                    navController.navigate(Screens.AUDITORY_FILTERS.route)
                },
            ) {
                Text(text = "Изменить")
            }
        }
    }
}

*/


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState", "RememberReturnType")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentDateSectionAuditory(navController: NavController, viewModel: MainViewModel, sheetState: BottomSheetState) {
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
                text = mutableStateCurrentAuditory.value,
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyCalendarSectionAuditory(viewModel: MainViewModel) {
    val context = LocalContext.current
    val filtersMessage = stringResource(id = R.string.ErrorEmptyFilters)

    var calendarElements = viewModel.calendarElementsAuditory

    var items by remember {
        mutableStateOf(
            (0..5).map {i ->
                CalendarTimeTable(
                    dayOfTheMonth = calendarElements,
                    dayOfTheWeek = calendarElements,
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
                            filtersAuditory.currentDay = items[it].dayOfTheWeek.toString()

                            if (filtersAuditory.auditory != "" && filtersAuditory.week != "" && filtersAuditory.corpus != "") {
                                viewModel.viewModelScope.launch(Dispatchers.IO) {
                                    viewModel.sendAuditoryFilters(
                                        filtersAuditory.auditory,
                                        filtersAuditory.corpus,
                                        filtersAuditory.week,
                                        calendarElements[it].day_of_the_week
                                    )
                                }
                            } else {
                                displayError(context, filtersMessage)
                            }
                        },
                    backgroundColor = Color.White
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(isCalendarAuditoryTextVisible.value){
                            Text(
                                text = calendarElements[it].date,
                                //text = mutableStateDate.value,
                                //text = items[it].dayOfTheMonth,
                                color = if (items[it].isSelected) calendarSelectedItemColor else SubjectsTextColor
                            )
                            Text(
                                text = calendarElements[it].day_of_the_week,
                                //text = items[it].dayOfTheWeek,x
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
fun AuditoryFiltersScreen(navController: NavController, viewModel: MainViewModel, sheetState: BottomSheetState){

    val context = LocalContext.current
    val filtersMessage = stringResource(id = R.string.ErrorEmptyFilters)
    var auditoryList: List<String> = listOf("Не выбрано")
    var corpusList: List<String> = listOf("Не выбрано")
    var weekList: List<String> = listOf("Не выбрано")
    val scope = rememberCoroutineScope()

    val loadedFilters = viewModel.loadedAuditoryFilters.observeAsState(listOf()).value

    var loadedAuditoryFromFilters = viewModel.auditoryFromFilters.observeAsState(listOf()).value
    val loadedCalendar = viewModel.daysOfWeek.observeAsState(listOf()).value

    viewModel.viewModelScope.launch {
        viewModel.getFilterFacultyCourseWeek()
    }

    if(filtersAuditory.auditory != "" && filtersAuditory.week != "" && filtersAuditory.corpus != ""){
        viewModel.viewModelScope.launch {
            viewModel.getDaysOfWeek(filtersAuditory.week)
        }
    }

    loadedFilters.forEach{ it ->
        weekList = it.week
        corpusList = it.corpus
        auditoryList = it.auditory
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
                            text = "Фильтры аудитории",
                            color = Color.Black,
                            fontSize = 15.sp
                        )

                    }
                    Column(){
                        filtersAuditory.corpus = GetOutlinedTextField(corpusList, "Введите корпус", "Corpus", "Auditory")
                    }
                    Column(){
                        filtersAuditory.week = GetOutlinedTextField(weekList,"Введите неделю", "Week", "Auditory")
                    }

                    viewModel.auditoryByFilter(filtersAuditory.corpus)
                    loadedAuditoryFromFilters.forEach {
                        auditoryList = it.auditoryName
                    }

                    Column(){
                        filtersAuditory.auditory = GetOutlinedTextField(weekList, "Введите аудиторию", "Auditory", "Auditory")
                    }
                    /*if(filters.faculty != "" && filters.course != "")
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
                    }*/
                }
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Button(
                    onClick = {
                        if(filtersAuditory.auditory != "" && filtersAuditory.week != "" && filtersAuditory.corpus != ""){

                            viewModel._calendarElementsAuditory.clear()

                            viewModel.viewModelScope.launch {
                                loadedCalendar.forEach { calendar ->
                                    Log.d("FFFFF",calendar.day_of_the_week.toString())
                                    Log.d("KKKKK",calendar.date)

                                    viewModel.addElementAuditoryFromServer(calendar)
                                }
                            }
                            Log.d("SIZE",loadedCalendar.size.toString())

                            mutableStateCurrentAuditory.value = filters.group
                            isCalendarAuditoryTextVisible.value = true
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
}