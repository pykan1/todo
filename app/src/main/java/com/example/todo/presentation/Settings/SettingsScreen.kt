package com.example.todo.presentation.Settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel) {
    val viewModel = hiltViewModel<SettingsViewModel>()
}