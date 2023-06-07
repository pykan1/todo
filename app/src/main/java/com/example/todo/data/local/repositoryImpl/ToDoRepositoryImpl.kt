package com.example.todo.data.local.repositoryImpl

import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.local.model.ToDo
import com.example.todo.data.local.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoRepository: ToDoRepository
){
    suspend fun getAllToDo(): List<ToDo> = toDoRepository.getAllToDo()

    suspend fun insertToDo(toDo: ToDo) = toDoRepository.insertToDo(toDo)

    suspend fun getToDoById(todoId: Long): ToDo = toDoRepository.getToDoById(todoId)

    suspend fun changeDone(todoId: Long, new: Boolean) = toDoRepository.changeDone(todoId, new)

    suspend fun getToDoByDate(date: String): List<ToDo> = toDoRepository.getToDoByDate(date)

    suspend fun changePriority(newPriority: Boolean, todoId: Long) = toDoRepository.changePriority(todoId, newPriority)
}