package com.example.timetable.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.example.timetable.ui.theme.doneTextSubjectColor
import com.example.timetable.ui.theme.lineColor
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

// @Inject constructor(private val responseTimeTableGroup: ResponseTimeTableGroup): CurrentSubject{
open class CurrentSubjectGroup @Inject constructor(private val responseTimeTableGroup: ResponseTimeTableGroup){

    @Composable
    fun getColor(ifLessColor: Color, ifMoreColor: String?) = when(ifMoreColor){
        "1" -> Color.Red
        "2" -> Color.Blue
        "3" -> Color.Green
        "4" -> Color.Cyan
        else -> {}
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val hour = LocalTime.now(ZoneId.of("Europe/Paris")).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private var refactorHour = hour.substring(0, 5).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentMil = refactorHour.replace(":",".")
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentTime = currentMil.toFloat()+3f

    val startSubject = responseTimeTableGroup.startSubject.replace(":",".").toFloat()
    val endSubject = responseTimeTableGroup.endSubject.replace(":",".").toFloat()

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun isPrevOrNextSubject(): TextStyle{
        return if(currentTime > endSubject){
            TextStyle(textDecoration = TextDecoration.LineThrough)
        }else{
            TextStyle()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorRadioButton(isLessColor: Color, isMoreColor: Color): RadioButtonColors {
        return if(hour > responseTimeTableGroup.endSubject!!.replace(':', '.').toFloat().toString()) RadioButtonDefaults.colors(isLessColor)  else RadioButtonDefaults.colors(isMoreColor)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorPrevAndNextSubject(ifLessColor: Color, ifMoreColor: Color): Color {
        return if(currentTime > endSubject) ifLessColor  else ifMoreColor
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getIsCurrentSubject(): Boolean{
        return currentTime in startSubject..endSubject
    }
}

class CurrentSubjectTeacher @Inject constructor(private val responseTeacherTimeTable: ResponseTeacherTimeTable){ // parametr responseTimeTableGroup: ResponseTimeTableGroup
    @RequiresApi(Build.VERSION_CODES.O)
    private val hour = LocalTime.now(ZoneId.of("Europe/Paris")).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private var refactorHour = hour.substring(0, 5).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentMil = refactorHour.replace(":",".")
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentTime = currentMil.toFloat()+4f

    val startSubject = responseTeacherTimeTable.startSubject.replace(":",".").toFloat()
    val endSubject = responseTeacherTimeTable.endSubject.replace(":",".").toFloat()

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun isPrevOrNextSubject(): TextStyle{
        return if(currentTime > endSubject){
            TextStyle(textDecoration = TextDecoration.LineThrough)
        }else{
            TextStyle()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorRadioButton(isLessColor: Color, isMoreColor: Color): RadioButtonColors {
        return if(currentTime > endSubject) RadioButtonDefaults.colors(isLessColor)  else RadioButtonDefaults.colors(isMoreColor)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorPrevAndNextSubject(ifLessColor: Color, ifMoreColor: Color): Color {
        return if(currentTime > endSubject) ifLessColor  else ifMoreColor
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorPrevAndNextSubjectFromServer(ifLessColor: Color, ifMoreColor: String): Color {
        return if(currentTime > endSubject) ifLessColor  else stringToColor(ifMoreColor)
    }

    @Composable
    fun stringToColor(value: String): Color = when(value){
        "0xFF7f85fd" -> lineColor
        else -> {doneTextSubjectColor}
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getIsCurrentSubject(responseTeacherTimeTable: ResponseTeacherTimeTable): Boolean{
        return currentTime in startSubject..endSubject
    }
}

class CurrentSubjectAuditory @Inject constructor(private val responseAuditoryTimeTable: ResponseAuditoryTimeTable){
    @RequiresApi(Build.VERSION_CODES.O)
    private val hour = LocalTime.now(ZoneId.of("Europe/Paris")).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private var refactorHour = hour.substring(0, 5).toString()
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentMil = refactorHour.replace(":",".")
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentTime = currentMil.toFloat()+4f

    val startSubject = responseAuditoryTimeTable.startSubject.replace(":",".").toFloat()
    val endSubject = responseAuditoryTimeTable.endSubject.replace(":",".").toFloat()

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun isPrevOrNextSubject(): TextStyle{
        return if(currentTime > endSubject){
            TextStyle(textDecoration = TextDecoration.LineThrough)
        }else{
            TextStyle()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorRadioButton(isLessColor: Color, isMoreColor: Color): RadioButtonColors {
        return if(currentTime > endSubject) RadioButtonDefaults.colors(isLessColor)  else RadioButtonDefaults.colors(isMoreColor)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun colorPrevAndNextSubject(ifLessColor: Color, ifMoreColor: Color): Color {
        return if(currentTime > endSubject) ifLessColor  else ifMoreColor
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getIsCurrentSubject(responseAuditoryTimeTable: ResponseAuditoryTimeTable): Boolean{
        return currentTime in startSubject..endSubject
    }

    /*@RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun getIsCurrentSubject(responseAuditoryTimeTable: ResponseAuditoryTimeTable): Boolean{
        val current = LocalTime.now(ZoneId.of("Europe/Paris")).toString()

        val hour = current.substring(0,current.indexOf(":")).trim()
        val startSub = responseAuditoryTimeTable.startSubject?.replace(':','.')?.toFloat()
        val endSub = responseAuditoryTimeTable.endSubject?.replace(':','.')?.toFloat()

        return hour.toFloat()+4f in startSub!!..endSub!!
    }*/
}