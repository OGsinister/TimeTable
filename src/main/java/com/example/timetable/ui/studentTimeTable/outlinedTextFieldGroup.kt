package com.example.timetable.ui.studentTimeTable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import com.example.timetable.R
import com.example.timetable.ui.filtersAuditory
import com.example.timetable.ui.teacherTimeTable.filtersTeacher
import com.example.timetable.viewModel.GroupFiltersViewModel
import com.example.timetable.viewModel.MainViewModel

@Composable
fun getOutlinedTextFieldGroup(list: List<String>, viewModel: MainViewModel): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.onSecondary,
                focusedLabelColor = MaterialTheme.colors.onSecondary,
                unfocusedLabelColor = MaterialTheme.colors.onSecondary
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintGroup),
                    color = MaterialTheme.colors.onSecondary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colors.background)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onGroupChanged(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.onSecondary)
                }
            }
        }
    }

    return selectedItem
}

@Composable
fun getOutlinedTextFieldWeek(list: List<String>, viewModel: MainViewModel): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.onSecondary,
                focusedLabelColor = MaterialTheme.colors.onSecondary,
                unfocusedLabelColor = MaterialTheme.colors.onSecondary
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintWeek),
                    color = MaterialTheme.colors.onSecondary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colors.background)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onWeeksChanged(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.onSecondary)
                }
            }
        }
    }
    return selectedItem
}

@Composable
fun getOutlinedTextFieldFaculty(list: List<String>, viewModel: MainViewModel): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.onSecondary,
                focusedLabelColor = MaterialTheme.colors.onSecondary,
                unfocusedLabelColor = MaterialTheme.colors.onSecondary
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintFaculty),
                    color = MaterialTheme.colors.onSecondary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colors.background)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onFacultyChanged(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.onSecondary)
                }
            }
        }
    }

    return selectedItem
}

@Composable
fun getOutlinedTextFieldCourse(list: List<String>, viewModel: MainViewModel): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary,
                textColor = MaterialTheme.colors.onSecondary,
                focusedLabelColor = MaterialTheme.colors.onSecondary,
                unfocusedLabelColor = MaterialTheme.colors.onSecondary
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintCourse),
                    color = MaterialTheme.colors.onSecondary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colors.background)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onCourseChanged(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.onSecondary)
                }
            }
        }
    }

    return selectedItem
}