package com.example.timetable.ui.teacherTimeTable

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
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.timetable.R
import com.example.timetable.data.model.CurrentSubjectGroup
import com.example.timetable.data.model.CurrentSubjectTeacher
import com.example.timetable.data.model.ResponseTeacherTimeTable
import com.example.timetable.data.model.ResponseTimeTableGroup
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeacherTimeTableListItem(responseTeacherTimeTable: ResponseTeacherTimeTable, navController: NavController) {
    val currentSubject = CurrentSubjectTeacher(responseTeacherTimeTable = responseTeacherTimeTable)

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
                    text = responseTeacherTimeTable.startSubject,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = responseTeacherTimeTable.endSubject,
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
            fun getSubjectColor(responseTeacherTimeTable: ResponseTeacherTimeTable) = when(responseTeacherTimeTable.lessonType){
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
                backgroundColor = currentSubject.colorPrevAndNextSubject(doneSubjectColor, getSubjectColor(responseTeacherTimeTable))
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
                            text = responseTeacherTimeTable.lessonType.toString(),
                            color = currentSubject.colorPrevAndNextSubject(
                                doneTextSubjectColor,
                                currentSubjectColor
                            ),
                            style = currentSubject.isPrevOrNextSubject()
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = responseTeacherTimeTable.classRoom.toString(),
                            color = currentSubject.colorPrevAndNextSubject(
                                doneTextSubjectColor,
                                currentSubjectColor
                            ),
                            style = currentSubject.isPrevOrNextSubject(),
                            fontFamily = FontFamily(Font(R.font.source_serif_bold)),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(
                                        responseTeacherTimeTable.classRoom.let{
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
                                    text = responseTeacherTimeTable.lessonName,
                                    color = if (responseTeacherTimeTable.classRoom == "") Color.White else currentSubject.colorPrevAndNextSubject(
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
                                    text = responseTeacherTimeTable.groupName,
                                    fontFamily = FontFamily(Font(R.font.source_serif_regular)),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp,
                                    color = currentSubject.colorPrevAndNextSubject(
                                        doneTextSubjectColor,
                                        currentSubjectColor
                                    ),
                                    //color = if(currentSubject.getIsCurrentSubject(responseTimeTableGroup)) currentSubjectColor else doneSubjectColor,
                                    style = currentSubject.isPrevOrNextSubject()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



/*






@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeacherTimeTableListItem(responseTeacherTimeTable: ResponseTeacherTimeTable, navController: NavController) {
    val currentSubject = CurrentSubjectTeacher(responseTeacherTimeTable = responseTeacherTimeTable)

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
                responseTeacherTimeTable.startSubject?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
                responseTeacherTimeTable.endSubject?.let {
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
                        colors = if (currentSubject.getIsCurrentSubject(responseTeacherTimeTable)) RadioButtonDefaults.colors(
                            Color.Blue
                        ) else RadioButtonDefaults.colors(Color.Gray),
                        selected = currentSubject.getIsCurrentSubject(responseTeacherTimeTable),
                        onClick = {}
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    currentSubject.colorPrevAndNextSubject(doneSubjectColor, lineColor)?.let {
                        Divider(
                            color = (if(currentSubject.getIsCurrentSubject(responseTeacherTimeTable)) lineColor else doneSubjectColor) as Color,
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
            responseTeacherTimeTable.lessonTypeColor.let {
                if (it != null) {
                    currentSubject.colorPrevAndNextSubjectFromServer(doneTextSubjectColor, it)?.let {
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
                                        text = responseTeacherTimeTable.lessonType.toString(),
                                        color = currentSubject.colorPrevAndNextSubject(
                                            doneTextSubjectColor,
                                            currentSubjectColor
                                        )!!,
                                        style = currentSubject.isPrevOrNextSubject()
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = responseTeacherTimeTable.classRoom.toString(),
                                        color = currentSubject.colorPrevAndNextSubject(
                                            doneTextSubjectColor,
                                            currentSubjectColor
                                        )!!,
                                        style = currentSubject.isPrevOrNextSubject(),
                                        fontFamily = FontFamily(Font(R.font.source_serif_bold)),
                                        modifier = Modifier
                                            .clickable {
                                                //navController.navigate(Screens.AUDITORY.withArgs(teacherTimeTable.classRoom))
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
                                                text = responseTeacherTimeTable.groupName.toString(),
                                                color = currentSubject.colorPrevAndNextSubject(
                                                    doneTextSubjectColor,
                                                    currentSubjectColor
                                                )!!,
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
                                                text = responseTeacherTimeTable.groupName.toString(),
                                                fontFamily = FontFamily(Font(R.font.source_serif_regular)),
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 15.sp,
                                                color = currentSubject.colorPrevAndNextSubject(
                                                    doneTextSubjectColor,
                                                    currentSubjectColor
                                                )!!,
                                                //color = if(getIsCurrentSubject(timeTable = timeTable)) currentSubjectColor else doneSubjectColor,
                                                style = currentSubject.isPrevOrNextSubject()
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
        }
}

@Composable
fun convertToColor(value: String): android.graphics.Color {
    val intValue = value.toInt()
    return intValue.toColor()
}

@Composable
fun getColor(value: String): Int {
    return value.toColorInt()
}
*/
