package com.example.todo.data.local.repositoryImpl

import com.example.todo.data.local.model.ListTask
import com.example.todo.data.local.model.ToDo
import com.example.todo.data.local.repository.ListTaskRepository
import javax.inject.Inject

class ListTaskRepositoryImpl @Inject constructor(
    private val listTaskRepository: ListTaskRepository
) {
    suspend fun insertListTask(listTask: ListTask) = listTaskRepository.insertListTask(listTask)

    suspend fun getAllListTask() = listTaskRepository.getAllListTask()

    suspend fun changeToDos(newToDos: List<ToDo>, id: Long) = listTaskRepository.changeToDos(newToDos, id)
}