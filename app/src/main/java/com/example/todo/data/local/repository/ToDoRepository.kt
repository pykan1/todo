package com.example.todo.data.local.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.local.model.ToDo

@Dao
interface ToDoRepository {
    @Insert
    suspend fun insertToDo(dailyToDo: ToDo)

    @Query("select * from todo")
    suspend fun getAllToDo(): List<ToDo>

    @Query("select * from todo WHERE todoId=:todoId")
    suspend fun getToDoById(todoId: Long): ToDo

    @Query("UPDATE todo SET done=:newDone WHERE todoId=:todoId")
    suspend fun changeDone(todoId: Long, newDone: Boolean)

    @Query("UPDATE todo SET priority=:newPriority WHERE todoId=:todoId")
    suspend fun changePriority(todoId: Long, newPriority: Boolean)

    @Query("select * from todo WHERE date=:date")
    suspend fun getToDoByDate(date: String): List<ToDo>
}
