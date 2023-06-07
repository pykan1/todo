package com.example.todo.domain.usecase

import com.example.todo.data.local.model.ToDo
import com.example.todo.data.local.repositoryImpl.ToDoRepositoryImpl
import javax.inject.Inject

class InsertTodoUseCase @Inject constructor(
    private val toDoRepositoryImpl: ToDoRepositoryImpl
) {
    suspend fun invoke(toDo: ToDo) = toDoRepositoryImpl.insertToDo(toDo)
}