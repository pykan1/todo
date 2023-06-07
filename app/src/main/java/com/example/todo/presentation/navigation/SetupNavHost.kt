package com.example.todo.presentation.navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Start
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.presentation.screens.Greeting.GreetingScreen
import com.example.todo.presentation.screens.Main.MainScreen


sealed class Screens(val rout: String, val icon: ImageVector) {
    object Greeting : Screens(rout = "greeting_screen", Icons.Default.Start)

    object Main : Screens(rout = "main_screen", Icons.Default.Domain)
    object Calendar : Screens(rout = "calendar_screen", Icons.Default.Home)
    object Emotion : Screens(rout = "emotion_screen", Icons.Default.Home)
    object Editor : Screens(rout = "editor_screen", Icons.Default.Edit)
    object Settings : Screens(rout = "settings_screen", Icons.Default.LocationOn)
}

@Composable
fun SetupNavHost (navController: NavHostController, viewModel: NavigationViewModel) {
    NavHost(
        navController = navController,
        startDestination = if (viewModel.days.isEmpty()) Screens.Greeting.rout else Screens.Calendar.rout
    ) {
        composable(route = Screens.Greeting.rout) {
            GreetingScreen(navController)
        }
        composable(route = Screens.Main.rout) {
            MainScreen(navController)
        }
        composable(route = Screens.Calendar.rout) {

        }
        composable(route = Screens.Emotion.rout) {

        }
        composable(route = Screens.Editor.rout) {

        }
        composable(route = Screens.Settings.rout) {

        }
    }
}