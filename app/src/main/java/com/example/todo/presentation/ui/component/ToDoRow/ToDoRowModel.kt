package com.example.todo.presentation.ui.component.ToDoRow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todo.data.local.model.ToDo

class ToDoRowModel: ViewModel() {
    var isDone by mutableStateOf(false)

    fun changeIsDone(toDo: ToDo) {
        isDone = !isDone
    }
}