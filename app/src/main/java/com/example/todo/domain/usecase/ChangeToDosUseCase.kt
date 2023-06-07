package com.example.todo.domain.usecase

import com.example.todo.data.local.model.ToDo
import com.example.todo.data.local.repositoryImpl.DayRepositoryImpl
import com.example.todo.data.local.repositoryImpl.ToDoRepositoryImpl
import javax.inject.Inject

class ChangeToDosUseCase @Inject constructor(
    private val dayRepositoryImpl: DayRepositoryImpl
){
    suspend fun invoke(newToDos: List<ToDo>, date: String) = dayRepositoryImpl.changeToDos(newToDos, date)
}