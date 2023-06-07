package com.example.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.local.model.Day
import com.example.todo.data.local.model.ToDo
import com.example.todo.data.local.repository.DayRepository
import com.example.todo.data.local.repository.ToDoRepository

@Database(entities = [ToDo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoRepository
}

@Database(entities = [Day::class], version = 1)
abstract class AppDatabase2: RoomDatabase() {
    abstract fun dayDao(): DayRepository
}