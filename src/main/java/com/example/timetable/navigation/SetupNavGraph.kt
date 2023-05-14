package com.example.timetable.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.timetable.ui.*
import com.example.timetable.ui.netrork.NoInternetScreen
import com.example.timetable.ui.studentTimeTable.FiltersScreen
import com.example.timetable.ui.studentTimeTable.StudentScreen
import com.example.timetable.ui.teacherTimeTable.TeacherScreen
import com.example.timetable.viewModel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: MainViewModel){
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    NavHost(
        navController = navController,
        startDestination = Screens.STUDENT.route
    ){
        composable(route = Screens.STUDENT_FILTERS.route){
            FiltersScreen(navController = navController,viewModel = viewModel, sheetState = sheetState)
        }

        composable(route = Screens.STUDENT.route){
            StudentScreen(navController = navController, viewModel = viewModel)
        }

        /*composable(
            route = Screens.STUDENT.route + "/{groupName}",
            arguments = listOf(
                navArgument("groupName"){
                    defaultValue = "EMPTYYYY"
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){backStackEntry ->
            backStackEntry.arguments?.getString("groupName")?.let {
                StudentScreen(navController = navController, viewModel = viewModel, groupName = it)
            }
        }*/

        composable(
            route = Screens.TEACHER.route + "/{teacherName}",
            arguments = listOf(
                navArgument("teacherName"){
                    defaultValue = "EMPTYYY"
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){backStackEntry ->
            backStackEntry.arguments?.getString("teacherName")?.let {
                TeacherScreen(viewModel = viewModel, navController = navController, teacherName = it)
            }
        }

        composable(route = Screens.AUDITORY.route){
            AuditoryScreen(navController = navController, viewModel = viewModel)
        }
        
       /* composable(
            route = Screens.AUDITORY.route + "/{auditoryName}",
            arguments = listOf(
                navArgument("auditoryName"){
                    defaultValue = "EMPTYYY"
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){backStackEntry ->
            backStackEntry.arguments?.getString("auditoryName")?.let{
                AuditoryScreen(navController = navController, viewModel = viewModel, auditoryName = it)
            }
        }*/

        composable(route = Screens.NO_INTERNET.route){
            NoInternetScreen(navController = navController, viewModel = viewModel)
        }
    }
}