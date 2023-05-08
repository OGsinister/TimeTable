package com.example.timetable.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.timetable.R

// TimeTable Card
val initSourceSerif = FontFamily(
    Font(R.font.source_serif_bold, FontWeight.Bold),
    Font(R.font.source_serif_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = initSourceSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = initSourceSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
)