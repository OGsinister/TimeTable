package com.example.timetable.ui.studentTimeTable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.timetable.R
import com.example.timetable.data.model.*
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.theme.*
import java.util.*

// fake compose
/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeTableListItem(responseTimeTableGroup: ResponseTimeTableGroup, navController: NavController) {
    val currentSubject = CurrentSubjectGroup(responseTimeTableGroup = responseTimeTableGroup)



    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .width(80.dp)
                .height(200.dp)
                .padding(start = 5.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                responseTimeTableGroup.startSubject?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
                responseTimeTableGroup.endSubject?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1,
                        color = SubjectsTextColor
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(20.dp)
                ) {
                    RadioButton(
                        */
/*colors = if (currentSubject.getIsCurrentSubject(timeTable)) RadioButtonDefaults.colors(
                            Color.Blue
                        ) else RadioButtonDefaults.colors(Color.Gray),*//*

                        colors = RadioButtonDefaults.colors(Color.Blue),
                        selected = currentSubject.getIsCurrentSubject(responseTimeTableGroup = responseTimeTableGroup),
                        onClick = {}
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    currentSubject.colorPrevAndNextSubject(doneSubjectColor, lineColor)?.let {
                        Divider(
                            color = it,
                            //color = if(getIsCurrentSubject(timeTable = timeTable)) lineColor else doneSubjectColor,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(2.5.dp)
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            responseTimeTableGroup.lessonTypeColor.let {
                currentSubject.colorPrevAndNextSubject(doneSubjectColor, getSubjectColor(it))?.let {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {},
                        backgroundColor = it
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = responseTimeTableGroup.lessonType.toString(),
                                    color = currentSubject.colorPrevAndNextSubject(doneTextSubjectColor, currentSubjectColor)!!,
                                    style = currentSubject.isPrevOrNextSubject()
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = responseTimeTableGroup.classRoom.toString(),
                                    color = currentSubject.colorPrevAndNextSubject(doneTextSubjectColor, currentSubjectColor)!!,
                                    style = currentSubject.isPrevOrNextSubject(),
                                    fontFamily = FontFamily(Font(R.font.source_serif_bold)),
                                    modifier = Modifier
                                        .clickable{
                                            navController.navigate(Screens.AUDITORY.withArgs(responseTimeTableGroup.classRoom))
                                        }
                                )
                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = if(responseTimeTableGroup.classRoom == "") "Окно" else responseTimeTableGroup.lessonName.toString(),
                                            color = if(responseTimeTableGroup.classRoom == "") Color.White else currentSubject.colorPrevAndNextSubject(doneTextSubjectColor, currentSubjectColor)!!,
                                            style = currentSubject.isPrevOrNextSubject(),
                                            fontFamily = FontFamily(Font(R.font.source_serif_light)),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 18.sp
                                        )
                                    }
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = responseTimeTableGroup.teacherName.toString(),
                                            fontFamily = FontFamily(Font(R.font.source_serif_regular)),
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 15.sp,
                                            color = currentSubject.colorPrevAndNextSubject(doneTextSubjectColor, currentSubjectColor)!!,
                                            //color = if(getIsCurrentSubject(timeTable = timeTable)) currentSubjectColor else doneSubjectColor,
                                            style = currentSubject.isPrevOrNextSubject(),
                                            modifier = Modifier
                                                .clickable {
                                                    //navController.navigate(Screens.TEACHER.route + "${timeTable.teacherName}")
                                                    navController.navigate(Screens.TEACHER.withArgs(responseTimeTableGroup.teacherName))
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            }
        }

*/

        /*Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .width(80.dp)
                    .height(200.dp)
                    .padding(start = 5.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    timeTable.startSubject?.let {
                        Text(
                            text = it
                        )
                    }
                    Spacer(modifier = Modifier.padding(3.dp))
                    timeTable.endSubject?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                color = SubjectsTextColor
                            )
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .height(20.dp)
                    ) {
                        RadioButton(
                            colors = if(getIsCurrentSubject(timeTable)) RadioButtonDefaults.colors(Color.Blue) else RadioButtonDefaults.colors(Color.Gray),
                            selected = getIsCurrentSubject(timeTable = timeTable),
                            onClick = {}
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Divider(
                            color = Color.Blue,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(2.5.dp)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {},
                backgroundColor = if(getIsCurrentSubject(timeTable)) Color.Red else Color.Gray
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Text(
                            //text = lessonName,
                            text = timeTable.lessonName.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(3.dp)
                        ){
                            Text(
                                //text = classRoom,
                                text = timeTable.classRoom.toString(),
                                color = Color.White
                            )
                        }

                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = timeTable.teacherName.toString(),
                            style = MaterialTheme.typography.h6,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = timeTable.lessonType.toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable { }
                        )
                    }
                }
            }
        }*/
//}
// Real compose

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeTableListItem(responseTimeTableGroup: ResponseTimeTableGroup, navController: NavController) {
    val currentSubject = CurrentSubjectGroup(responseTimeTableGroup = responseTimeTableGroup)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .width(80.dp)
                .height(200.dp)
                .padding(start = 5.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Text(
                    text = responseTimeTableGroup.startSubject,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = responseTimeTableGroup.endSubject,
                    style = MaterialTheme.typography.body1,
                    color = SubjectsTextColor
                )
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(20.dp)
                ) {
                    RadioButton(
                        colors = if (currentSubject.getIsCurrentSubject()) RadioButtonDefaults.colors(
                            Color.Blue
                        ) else RadioButtonDefaults.colors(Color.Gray),
                        selected = currentSubject.getIsCurrentSubject(),
                        onClick = {}
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Divider(
                        color = currentSubject.colorPrevAndNextSubject(doneSubjectColor, lineColor),
                        //color = if(getIsCurrentSubject(timeTable = timeTable)) lineColor else doneSubjectColor,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.5.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            @Composable
            fun getSubjectColor(responseTimeTableGroup: ResponseTimeTableGroup) = when(responseTimeTableGroup.lessonType){
                "Lecture" -> lectureColor
                "Practice" -> practiceColor
                "Exam" -> examColor
                "Lab" -> labColor
                else -> { armyColor }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {},
                backgroundColor = currentSubject.colorPrevAndNextSubject(doneSubjectColor, getSubjectColor(responseTimeTableGroup))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = responseTimeTableGroup.lessonType.toString(),
                            color = currentSubject.colorPrevAndNextSubject(
                                doneTextSubjectColor,
                                currentSubjectColor
                            ),
                            style = currentSubject.isPrevOrNextSubject()
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = responseTimeTableGroup.classRoom.toString(),
                            color = currentSubject.colorPrevAndNextSubject(
                                doneTextSubjectColor,
                                currentSubjectColor
                            ),
                            style = currentSubject.isPrevOrNextSubject(),
                            fontFamily = FontFamily(Font(R.font.source_serif_bold)),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(
                                        responseTimeTableGroup.classRoom.let{
                                            Screens.AUDITORY.withArgs(it)
                                        }
                                    )
                                }
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = responseTimeTableGroup.lessonName,
                                    color = if (responseTimeTableGroup.classRoom == "") Color.White else currentSubject.colorPrevAndNextSubject(
                                        doneTextSubjectColor,
                                        currentSubjectColor
                                    ),
                                    style = currentSubject.isPrevOrNextSubject(),
                                    fontFamily = FontFamily(Font(R.font.source_serif_light)),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = responseTimeTableGroup.teacherName.toString(),
                                    fontFamily = FontFamily(Font(R.font.source_serif_regular)),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp,
                                    color = currentSubject.colorPrevAndNextSubject(
                                        doneTextSubjectColor,
                                        currentSubjectColor
                                    ),
                                    //color = if(currentSubject.getIsCurrentSubject(responseTimeTableGroup)) currentSubjectColor else doneSubjectColor,
                                    style = currentSubject.isPrevOrNextSubject(),
                                    modifier = Modifier
                                        .clickable {
                                            //navController.navigate(Screens.TEACHER.route + "${timeTable.teacherName}")
                                            navController.navigate(
                                                responseTimeTableGroup.teacherName.let{
                                                    Screens.TEACHER.withArgs(it)
                                                }
                                            )
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



