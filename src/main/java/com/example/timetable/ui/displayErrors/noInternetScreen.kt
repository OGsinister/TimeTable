package com.example.timetable.ui.displayErrors

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timetable.R

@Composable
fun noInternetScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_internet),
                contentDescription = "",
                modifier = Modifier
                    .size(256.dp)
            )
            Text(
                text = stringResource(id = R.string.NoInternet),
                fontSize = 16.sp
            )
        }
    }
}