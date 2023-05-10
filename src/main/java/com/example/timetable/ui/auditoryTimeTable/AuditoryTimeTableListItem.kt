package com.example.timetable.ui.auditoryTimeTable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.timetable.data.model.Auditory
import com.example.timetable.data.model.CurrentSubjectAuditory
import com.example.timetable.data.model.CurrentSubjectTeacher
import com.example.timetable.data.model.ResponseAuditoryTimeTable
import com.example.timetable.data.model.ResponseTimeTableGroup
import com.example.timetable.data.model.TimeTable
import com.example.timetable.navigation.Screens
import com.example.timetable.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuditoryTimeTableListItem(responseAuditoryTimeTable: ResponseAuditoryTimeTable, navController: NavController){
    val currentSubject = CurrentSubjectAuditory(responseAuditoryTimeTable = responseAuditoryTimeTable)

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
                responseAuditoryTimeTable.startSubject?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
                responseAuditoryTimeTable.endSubject?.let {
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
                        colors = currentSubject.colorRadioButton(Color.Gray, Color.Blue),
                        /*colors = if (getIsCurrentSubject(timeTable)) RadioButtonDefaults.colors(
                            Color.Blue
                        ) else RadioButtonDefaults.colors(Color.Gray),*/
                        selected = currentSubject.getIsCurrentSubject(responseAuditoryTimeTable = responseAuditoryTimeTable),
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
            @Composable
            fun getSubjectColor(responseAuditoryTimeTable: ResponseAuditoryTimeTable) = when(responseAuditoryTimeTable.lessonType){
                "Lecture" -> lectureColor
                "Practice" -> practiceColor
                "Exam" -> examColor
                "Lab" -> labColor
                else -> {
                    armyColor}
            }

            responseAuditoryTimeTable.lessonType.let {
                currentSubject.colorPrevAndNextSubject(doneSubjectColor, getSubjectColor(responseAuditoryTimeTable = responseAuditoryTimeTable))?.let {
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
                                    text = responseAuditoryTimeTable.lessonType.toString(),
                                    color = currentSubject.colorPrevAndNextSubject(
                                        doneTextSubjectColor,
                                        currentSubjectColor
                                    )!!,
                                    style = currentSubject.isPrevOrNextSubject()
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = responseAuditoryTimeTable.teacherName,
                                    color = currentSubject.colorPrevAndNextSubject(
                                        doneTextSubjectColor,
                                        currentSubjectColor
                                    )!!,
                                    style = currentSubject.isPrevOrNextSubject(),
                                    fontFamily = FontFamily(Font(R.font.source_serif_bold)),
                                    modifier = Modifier
                                        .clickable {
                                            //navController.navigate(Screens.AUDITORY.withArgs(timeTableAuditory.classRoom))
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
                                            text = responseAuditoryTimeTable.lessonName.toString(),
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
                                            text = responseAuditoryTimeTable.group.toString(),
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