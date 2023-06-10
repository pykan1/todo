package com.example.todo.presentation.Settings

import androidx.lifecycle.ViewModel
import com.example.todo.data.local.model.Settings
import com.example.todo.domain.usecase.ChangeNotificationUseCase
import com.example.todo.domain.usecase.ChangeThemeUseCase
import com.example.todo.domain.usecase.GetSettingsUseCase
import com.example.todo.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val changeThemeUseCase: ChangeThemeUseCase,
    private val changeNotificationUseCase: ChangeNotificationUseCase
) : ViewModel() {
    val stateSettings = MutableStateFlow(Settings())
}