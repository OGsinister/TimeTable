package com.example.timetable.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.timetable.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupFiltersViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val faculty = savedStateHandle.getStateFlow("faculty", "ФИРТ")
    val course = savedStateHandle.getStateFlow("course", "3")
    val week = savedStateHandle.getStateFlow("week", "3")
    val group = savedStateHandle.getStateFlow("group", "ПРО-329")

    fun onFacultyChanged(NewValue: String) {
        savedStateHandle["faculty"] = NewValue
    }

    fun onCourseChanged(NewValue: String) {
        savedStateHandle["course"] = NewValue
    }

    fun onWeeksChanged(NewValue: String) {
        savedStateHandle["week"] = NewValue
    }

    fun onGroupChanged(NewValue: String) {
        savedStateHandle["group"] = NewValue
    }
}