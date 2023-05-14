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
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.timetable.R
import com.example.timetable.data.model.CalendarTimeTable
import com.example.timetable.data.model.FiltersAuditory
import com.example.timetable.getErrorEmptyFiltersError
import com.example.timetable.localizedCurrentMonth
import com.example.timetable.ui.auditoryTimeTable.AuditoryTimeTableListItem
import com.example.timetable.ui.auditoryTimeTable.getOutlinedTextFieldAuditory
import com.example.timetable.ui.auditoryTimeTable.getOutlinedTextFieldCorpus
import com.example.timetable.ui.auditoryTimeTable.getOutlinedTextFieldWeekAuditory
import com.example.timetable.ui.displayErrors.displayError
import com.example.timetable.ui.studentTimeTable.filters
import com.example.timetable.ui.studentTimeTable.getOutlinedTextFieldCourse
import com.example.timetable.ui.studentTimeTable.getOutlinedTextFieldFaculty
import com.example.timetable.ui.studentTimeTable.getOutlinedTextFieldGroup
import com.example.timetable.ui.studentTimeTable.getOutlinedTextFieldWeek
import com.example.timetable.ui.studentTimeTable.isCalendarTextVisible
import com.example.timetable.ui.theme.SubjectsTextColor
import com.example.timetable.ui.theme.calendarSelectedItemColor
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

var filtersAuditory = FiltersAuditory
var isCalendarAuditoryTextVisible = mutableStateOf(false)

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuditoryScreen(navController: NavHostController, viewModel: MainViewModel){

    var auditoryTimeTable = viewModel.auditoryTimeTable.observeAsState(listOf()).value

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)



    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        drawerShape = RoundedCornerShape(10),
        sheetContent = {
            AuditoryFiltersScreen(
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

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState", "RememberReturnType",
    "CoroutineCreationDuringComposition"
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentDateSectionAuditory(navController: NavController, viewModel: MainViewModel, sheetState: BottomSheetState) {
    val scope = rememberCoroutineScope()
    val auditory = viewModel.auditory.collectAsStateWithLifecycle().value

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
                text = auditory,
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
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface),
                modifier = Modifier
                    .height(60.dp),
                onClick = {
                    scope.launch {
                        if(sheetState.isCollapsed) sheetState.expand() else sheetState.collapse()
                    }
                },
            ) {
                Text(
                    text = stringResource(id = R.string.ButtonText),
                    color = MaterialTheme.colors.background
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyCalendarSectionAuditory(viewModel: MainViewModel) {
    val context = LocalContext.current
    val filtersMessage = stringResource(id = R.string.ErrorEmptyFilters)

    val week = viewModel.weekAuditory.collectAsState().value
    val auditory = viewModel.auditory.collectAsState().value
    val corpus = viewModel.corpusAuditory.collectAsState().value
    val calendarElements = viewModel.calendarElementsAuditory
    val currentDay = viewModel.currentDayAuditory.collectAsState().value

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

                            viewModel.onDayChangedAuditory(calendarElements[it].day_of_the_week)
                            Log.d("CHECKAuditory", "$week $auditory $currentDay")

                            if (auditory != "" && week != "" && corpus != "") {
                                viewModel.viewModelScope.launch(Dispatchers.IO) {
                                    viewModel.sendAuditoryFilters(
                                        auditory,
                                        week,
                                        currentDay
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
    val currentLocation = Locale.current

    val week = viewModel.weekAuditory.collectAsStateWithLifecycle().value
    val auditory = viewModel.auditory.collectAsStateWithLifecycle().value
    val corpus = viewModel.corpusAuditory.collectAsStateWithLifecycle().value

    var auditoryList: List<String> = listOf("Не выбрано")
    var corpusList: List<String> = listOf("Не выбрано")
    var weekList: List<String> = listOf("Не выбрано")
    val scope = rememberCoroutineScope()

    val loadedFilters = viewModel.loadedAuditoryFilters.observeAsState(listOf()).value

    var loadedAuditoryFromFilters = viewModel.auditoryFromFilters.observeAsState(listOf()).value
    val loadedCalendar = viewModel.daysOfWeek.observeAsState(listOf()).value


    viewModel.viewModelScope.launch(Dispatchers.IO) {
        viewModel.getFiltersCorpusWeek()
    }

    viewModel.viewModelScope.launch {
        viewModel.getDaysOfWeek(week)
    }

    loadedFilters.forEach{ it ->
        weekList = it.week
        corpusList = it.сorps
    }

    loadedAuditoryFromFilters.forEach {
        auditoryList = it.auditoryName
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
                            text = stringResource(id = R.string.filterAuditoryText),
                            color = MaterialTheme.colors.surface,
                            fontSize = 15.sp
                        )

                    }
                    Column(){ // filters.faculty
                        getOutlinedTextFieldCorpus(list = corpusList, viewModel = viewModel)
                    }
                    Column(){
                        getOutlinedTextFieldAuditory(list = auditoryList, viewModel = viewModel)
                    }

                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        viewModel.auditoryByFilter(corpus)
                    }

                    loadedAuditoryFromFilters.forEach {
                        auditoryList = it.auditoryName
                    }

                    Column(){
                        getOutlinedTextFieldWeekAuditory(list = weekList, viewModel = viewModel)
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
                        if(auditory != "" && week != "" && corpus != ""){
                            viewModel._calendarElementsAuditory.clear()
                            viewModel.viewModelScope.launch {
                                loadedCalendar.forEach { calendar ->
                                    viewModel.addElementFromServerAuditory(calendar)
                                }
                            }
                            isCalendarAuditoryTextVisible.value = true

                            scope.launch {
                                sheetState.collapse()
                            }
                        }else{
                            displayError(context,getErrorEmptyFiltersError(currentLocation))
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface)
                ) {
                    Text(text = stringResource(id = R.string.saveButton))
                }
            }
        }
    }
}