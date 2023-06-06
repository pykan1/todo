package com.example.todo.presentation.navigation

import androidx.lifecycle.ViewModel
import com.example.todo.domain.usecase.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    getAllToDoUseCase: GetAllToDoUseCase
): ViewModel() {
    val toDo = runBlocking { getAllToDoUseCase.invoke() }

}