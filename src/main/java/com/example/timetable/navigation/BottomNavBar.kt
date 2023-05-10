package com.example.timetable.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.timetable.NameAppSection
import com.example.timetable.R
import com.example.timetable.viewModel.MainViewModel
import java.util.Locale

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach{item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name)

                        if(selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun getNavigation(viewModel: MainViewModel){
    val navController = rememberNavController()
    val currentLang = Locale.getDefault().language

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = if(currentLang == "en") "Group" else stringResource(id = R.string.Student_screen),
                        route = stringResource(id = R.string.Student_screen),
                        icon = Icons.Default.Edit
                    ),
                    BottomNavItem(
                        name = if(currentLang == "en") "Teacher" else stringResource(id = R.string.Teacher_screen),
                        route = stringResource(id = R.string.Teacher_screen) + "/{teacherName}",
                        icon = Icons.Default.Person
                    ),
                    BottomNavItem(
                        name = if(currentLang == "en") "Auditory" else stringResource(id = R.string.Auditory_screen),
                        route = stringResource(id = R.string.Auditory_screen),
                        icon = Icons.Default.List
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Column {
            NameAppSection()
            SetupNavGraph(navController = navController, viewModel = viewModel)
        }
    }

}
