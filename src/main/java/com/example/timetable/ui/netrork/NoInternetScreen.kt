package com.example.timetable.ui.netrork

import android.graphics.Paint.Style
import android.media.Image
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.Coil.imageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.example.timetable.R
import com.example.timetable.ui.theme.SubjectsTextColor
import com.example.timetable.ui.theme.TimeTableTheme
import com.example.timetable.viewModel.MainViewModel

@Composable
fun NoInternetScreen(navController: NavController, viewModel: MainViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painterResource(R.drawable.ic_no_internet),
                "content description",
                modifier = Modifier
                    .size(250.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.NoInternet),
                    style = TextStyle(
                        color = SubjectsTextColor,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}
