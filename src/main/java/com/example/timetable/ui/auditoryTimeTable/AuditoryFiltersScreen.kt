package com.example.timetable.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.timetable.GetOutlinedTextField
import com.example.timetable.R
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.displayErrors.displayError
import com.example.timetable.ui.studentTimeTable.filters
import com.example.timetable.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AuditoryFiltersScreen(navController: NavController, viewModel: MainViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    filters.week = GetOutlinedTextField(weekList, "Введите номер недели", "Week")
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    filters.building = GetOutlinedTextField(corpusList,"Введите корпус", "Building")
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    filters.auditory = GetOutlinedTextField(auditoryList, "Введите аудиторию", "Auditory")
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
            ){
                Button(
                    onClick = {
                        // Send data to server
                        if (filters.building != "" && filters.auditory != "") {
                            navController.navigate(Screens.AUDITORY.withArgs(filters.auditory.toString()))
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
}*/
