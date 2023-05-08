package com.example.timetable.ui.teacherTimeTable

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
import com.example.timetable.R
import com.example.timetable.viewModel.MainViewModel

@Composable
fun getOutlinedTextFieldTeacher(list: List<String>, viewModel: MainViewModel) {
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
            .background(Color.Gray)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                textColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.surface,
                unfocusedLabelColor = MaterialTheme.colors.surface
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintWeekTeacher),
                    color = MaterialTheme.colors.primary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.primary,
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
                .background(MaterialTheme.colors.surface)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onTeacherNameChangedTeacher(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.primary)
                }
            }
        }
    }
}

@Composable
fun getOutlinedTextFieldCafedra(list: List<String>, viewModel: MainViewModel) {
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
            .background(Color.Gray)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                textColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.surface,
                unfocusedLabelColor = MaterialTheme.colors.surface
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintCafedraTeacher),
                    color = MaterialTheme.colors.primary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.primary,
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
                .background(MaterialTheme.colors.surface)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onCafedraChangedTeacher(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.primary)
                }
            }
        }
    }
}

@Composable
fun getOutlinedTextFieldFacultyTeacher(list: List<String>, viewModel: MainViewModel) {
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
            .background(Color.Gray)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                textColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.surface,
                unfocusedLabelColor = MaterialTheme.colors.surface
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintFacultyTeacher),
                    color = MaterialTheme.colors.primary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.primary,
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
                .background(MaterialTheme.colors.surface)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onFacultyChangedTeacher(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.primary)
                }
            }
        }
    }
}

@Composable
fun getOutlinedTextFieldWeekTeacher(list: List<String>, viewModel: MainViewModel) {
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
            .background(Color.Gray)
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                textColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.surface,
                unfocusedLabelColor = MaterialTheme.colors.surface
            ),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                },
            label = {
                Text(
                    text = stringResource(id = R.string.OutlinedTextFieldHintWeekTeacher),
                    color = MaterialTheme.colors.primary
                ) },
            trailingIcon = {
                Icon(
                    icon,
                    "",
                    tint = MaterialTheme.colors.primary,
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
                .background(MaterialTheme.colors.surface)
        ) {
            list.forEach{
                DropdownMenuItem(onClick = {
                    selectedItem = it
                    viewModel.onWeeksChangedTeacher(selectedItem)
                    expanded = false
                }) {
                    Text(text = it, color = MaterialTheme.colors.primary)
                }
            }
        }
    }
}