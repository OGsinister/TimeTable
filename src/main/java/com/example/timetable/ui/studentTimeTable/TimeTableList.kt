package com.example.timetable.ui.studentTimeTable

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.timetable.data.model.TodosItem
import com.example.timetable.ui.theme.TimeTableTheme
import com.example.timetable.viewModel.MainViewModel

/*@Composable
fun TimeTableList(todosItem: TodosItem, viewModel: MainViewModel){
    //val timeTableList = remember { TimeTableDataProvider }
    val todos = viewModel.todos.observeAsState(listOf()).value

    todos.forEach {
        Log.d("checkData","ID: ${it.id} BRAND: ${it.title} TITLE: ${it.completed} ")
    }

    LazyColumn {
        items() {
            TimeTableListItem(todosItem)
        }
    }
}*/

/*@Composable
fun LinearProgressBarSection(){

    val dataProvider = remember {TimeTableDataProvider}
    val progressBarValue = dataProvider.progressBarValue.collectAsState(initial = 0)

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LinearProgressIndicator(
            color = Color.Blue,
            progress = progressBarValue.value.toFloat()

        )
    }
}*/
