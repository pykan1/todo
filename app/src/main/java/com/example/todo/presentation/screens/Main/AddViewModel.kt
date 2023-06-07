package com.example.todo.presentation.screens.Main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Day
import com.example.todo.data.local.model.ToDo
import com.example.todo.domain.usecase.ChangeToDosUseCase
import com.example.todo.domain.usecase.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val changeToDosUseCase: ChangeToDosUseCase,
    private val getToDosUseCase: GetAllToDoUseCase
): ViewModel() {
    var title by mutableStateOf("")

    fun setText(text: String) {
        title = text
    }

    fun addToDo(viewModel: MainViewModel) {
        viewModelScope.launch {
            val toDo = ToDo(
                title = title,
                date = viewModel.dayState.value.date
            )
            val newToDos = viewModel.dayState.value.toDos + toDo
            viewModel.dayState.emit(
                Day(
                    date = viewModel.dayState.value.date,
                    motivation = viewModel.dayState.value.date,
                    priorityToDos = viewModel.dayState.value.priorityToDos,
                    toDos = newToDos
                )
            )
            changeToDosUseCase.invoke(newToDos, viewModel.dayState.value.date)
        }
    }

}