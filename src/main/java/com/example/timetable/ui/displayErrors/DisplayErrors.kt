package com.example.timetable.ui.displayErrors

import android.content.Context
import android.widget.Toast

fun displayError(context: Context, message: String){
    return Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}