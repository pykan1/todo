package com.example.todo.data.local.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.local.model.Day
import com.example.todo.data.local.model.ToDo

@Dao
interface DayRepository {
    @Insert
    suspend fun insertDay(day: Day)

    @Query("select * from day WHERE date=:date")
    suspend fun getDayByDate(date: String): Day

    @Query("select * from day")
    suspend fun getAllDay(): List<Day>

    @Query("UPDATE day SET priorityToDos=:newPriority WHERE date=:date")
    suspend fun changePriorityToDos(newPriority: List<ToDo>, date: String)

    @Query("UPDATE day SET toDos=:newToDos WHERE date=:date")
    suspend fun changeToDos(newToDos: List<ToDo>, date: String)

}
