package com.example.todo.domain.usecase

import com.example.todo.data.local.repositoryImpl.ToDoRepositoryImpl
import javax.inject.Inject

class GetToDoByIdUseCase @Inject constructor(
    private val toDoRepositoryImpl: ToDoRepositoryImpl
) {
    suspend fun invoke(toDoId: Long) = toDoRepositoryImpl.getToDoById(toDoId)
}