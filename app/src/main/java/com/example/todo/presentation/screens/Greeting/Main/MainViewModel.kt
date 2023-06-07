package com.example.todo.presentation.screens.Greeting.Main

import androidx.lifecycle.ViewModel
import com.example.todo.domain.usecase.GetAllToDoUseCase
import com.example.todo.domain.usecase.GetToDoByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getToDoByDateUseCase: GetToDoByDateUseCase
): ViewModel() {

}