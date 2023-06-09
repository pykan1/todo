package com.example.todo.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Start
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.R
import com.example.todo.presentation.screens.Calendar.CalendarScreen
import com.example.todo.presentation.screens.Emotion.EmotionScreen
import com.example.todo.presentation.screens.Greeting.GreetingScreen
import com.example.todo.presentation.screens.List.ListScreen
import com.example.todo.presentation.screens.Main.MainScreen


sealed class Screens(val rout: String, val icon: Int) {
    object Greeting : Screens(rout = "greeting_screen", R.drawable.star)

    object Main : Screens(rout = "main_screen", R.drawable.main)
    object Calendar : Screens(rout = "calendar_screen", R.drawable.calendar)
    object Emotion : Screens(rout = "emotion_screen", R.drawable.emotion)
    object Editor : Screens(rout = "editor_screen", R.drawable.editor)
    object Settings : Screens(rout = "settings_screen", R.drawable.settings)
}

@Composable
fun SetupNavHost (navController: NavHostController, viewModel: NavigationViewModel) {
    NavHost(
        navController = navController,
        startDestination = if (viewModel.days.isEmpty()) Screens.Greeting.rout else Screens.Main.rout
    ) {
        composable(route = Screens.Greeting.rout) {
            GreetingScreen(navController, viewModel)
        }
        composable(route = Screens.Main.rout) {
            MainScreen(navController)
        }
        composable(route = Screens.Calendar.rout) {
            CalendarScreen(navController = navController)
        }
        composable(route = Screens.Emotion.rout) {
            EmotionScreen(navController = navController)
        }
        composable(route = Screens.Editor.rout) {
            ListScreen(navController = navController)
        }
        composable(route = Screens.Settings.rout) {

        }
    }
}