package com.example.todo.domain.usecase

import com.example.todo.data.local.repositoryImpl.ToDoRepositoryImpl
import javax.inject.Inject

class GetToDoByDateUseCase @Inject constructor(
    private val toDoRepositoryImpl: ToDoRepositoryImpl
) {
    suspend fun invoke(date: String) = toDoRepositoryImpl.getToDoByDate(date)
}