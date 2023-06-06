package com.example.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.local.model.ToDo
import com.example.todo.data.local.repository.ToDoRepository

@Database(entities = [ToDo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoRepository
}