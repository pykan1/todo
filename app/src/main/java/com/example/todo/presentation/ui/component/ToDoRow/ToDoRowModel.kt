package com.example.todo.presentation.ui.component.ToDoRow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.ToDo
import com.example.todo.presentation.screens.Main.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ToDoRowModel: ViewModel() {
    val stateToDo = MutableStateFlow(ToDo())

    var isDone by mutableStateOf(false)

    fun initToDo(toDo: ToDo) {
        viewModelScope.launch {
            stateToDo.emit(
                toDo
            )
        }
    }

    fun changeIsDone(mainViewModel: MainViewModel) {
        viewModelScope.launch{
            stateToDo.emit(
                ToDo(
                    title = stateToDo.value.title,
                    date = stateToDo.value.date,
                    priority = stateToDo.value.priority,
                    done = !stateToDo.value.done
                )
            )
            mainViewModel.updateToDo(stateToDo.value)
        }
    }

    fun changeIsPriority(mainViewModel: MainViewModel) {
        viewModelScope.launch{
            stateToDo.emit(
                ToDo(
                    title = stateToDo.value.title,
                    date = stateToDo.value.date,
                    priority = !stateToDo.value.priority,
                    done = stateToDo.value.done
                )
            )
            mainViewModel.updateToDo(stateToDo.value)
        }
    }
}