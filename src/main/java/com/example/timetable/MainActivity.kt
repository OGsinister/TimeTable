package com.example.timetable

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.timetable.navigation.SetupNavGraph
import com.example.timetable.navigation.getNavigation
import com.example.timetable.ui.filtersAuditory
import com.example.timetable.ui.studentTimeTable.filters
import com.example.timetable.ui.teacherTimeTable.filtersTeacher
import com.example.timetable.ui.theme.TimeTableTheme
import com.example.timetable.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.time.Month
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeTableTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                val navController = rememberNavController()

                SetupNavGraph(navController = navController, viewModel = viewModel)
                getNavigation(viewModel = viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        when(this.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)){
            Configuration.UI_MODE_NIGHT_NO ->{
                window.statusBarColor = resources.getColor(R.color.purple_700, null)
            }
            else -> {
                window.statusBarColor = resources.getColor(R.color.black,null)
            }
        }
    }
}

@Composable
fun NameAppSection(){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.ic_wave),
                contentScale = ContentScale.FillWidth
            )
    )
    {
        Text(
            color = MaterialTheme.colors.primaryVariant,
            text = stringResource(id = R.string.app_name),
            fontSize = 25.sp
        )
    }
}

@Composable
fun GetOutlinedTextField(
    list: List<String>,
    text: String,
    typeFilter: String,
    typeNav: String
): String{
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        if (typeNav == "Group") {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {selectedItem = it},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = MaterialTheme.colors.primary,
                    focusedLabelColor = MaterialTheme.colors.surface,
                    unfocusedLabelColor = MaterialTheme.colors.surface
                ),
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .onGloballyPositioned { layoutCoordinates ->
                        textFieldSize = layoutCoordinates.size.toSize()
                    },
                label = {
                    Text(
                        text = text,
                        color = MaterialTheme.colors.primary
                    ) },
                trailingIcon = {
                    Icon(
                        icon,
                        "",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                    )
                }
            )
        } else if (typeNav == "Teacher") {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {selectedItem = it},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = MaterialTheme.colors.primary,
                    focusedLabelColor = MaterialTheme.colors.surface,
                    unfocusedLabelColor = MaterialTheme.colors.surface
                ),
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .onGloballyPositioned { layoutCoordinates ->
                        textFieldSize = layoutCoordinates.size.toSize()
                    },
                label = {
                    Text(
                        text = text,
                        color = MaterialTheme.colors.primary
                    ) },
                trailingIcon = {
                    Icon(
                        icon,
                        "",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                    )
                }
            )
        } else if (typeNav == "Auditory") {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {selectedItem = it},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    textColor = MaterialTheme.colors.primary,
                    focusedLabelColor = MaterialTheme.colors.surface,
                    unfocusedLabelColor = MaterialTheme.colors.surface
                ),
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .onGloballyPositioned { layoutCoordinates ->
                        textFieldSize = layoutCoordinates.size.toSize()
                    },
                label = {
                    Text(
                        text = text,
                        color = MaterialTheme.colors.primary
                    ) },
                trailingIcon = {
                    Icon(
                        icon,
                        "",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                    )
                }
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colors.surface)
        ) {
            list.forEach{
                if(typeNav == "Group"){
                    DropdownMenuItem(onClick = {
                        selectedItem = it
                        expanded = false
                        when(typeFilter){
                            "Faculty" -> filters.faculty = it
                            "Course" -> filters.course = it
                            "Group" -> filters.group = it
                            "Week" -> filters.week = it
                        }
                    }) {
                        Text(text = it, color = MaterialTheme.colors.primary)
                    }
                }else if(typeNav == "Teacher"){
                    DropdownMenuItem(onClick = {
                        selectedItem = it
                        expanded = false
                        when(typeFilter){
                            "Faculty" -> filtersTeacher.faculty = it
                            "Cafedra" -> filtersTeacher.cafedra = it
                            "teacherName" -> filtersTeacher.teacher_name = it
                            "week" -> filtersTeacher.week = it
                        }
                    }) {
                        Text(text = it, color = MaterialTheme.colors.primary)
                    }
                }else if(typeNav == "Auditory"){
                    DropdownMenuItem(onClick = {
                        selectedItem = it
                        expanded = false
                        when(typeFilter){
                            "week" -> filtersAuditory.week = it
                            "corpus" -> filtersAuditory.corpus = it
                            "auditory" -> filtersAuditory.auditory = it
                        }
                    }) {
                        Text(text = it, color = MaterialTheme.colors.primary)
                    }
                }
            }
        }
    }
    return selectedItem
}

val currentLang = Locale.current.language

fun getErrorEmptyFiltersError(currentLocale: Locale) = when(currentLocale.language){
    "en" -> "Input filters"
    else -> {"Введите фильтры"}
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun localizedCurrentMonth(localDate: Month) = when(localDate){

    Month.JANUARY ->    if(currentLang == "en") "January" else "ЯНВАРЯ"
    Month.FEBRUARY ->   if(currentLang == "en") "February" else "ФЕВРАЛЯ"
    Month.MARCH ->      if(currentLang == "en") "March " else "МАРТА"
    Month.APRIL ->      if(currentLang == "en") "April " else  "АПРЕЛЯ"
    Month.MAY ->         "МАЯ"
    Month.JUNE ->       if(currentLang == "en") "June" else  "ИЮНЯ"
    Month.JULY ->       "ИЮЛЯ"
    Month.AUGUST ->     "АВГУСТА"
    Month.SEPTEMBER ->  "СЕНТЯБРЯ"
    Month.OCTOBER ->    "ОКТЯБРЯ"
    Month.NOVEMBER ->  "НОЯБРЯ"
    else -> {
        if(currentLang == "en") "March " else "ДЕКАБРЯ"
    }
}
