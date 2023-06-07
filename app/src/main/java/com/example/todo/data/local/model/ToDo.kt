package com.example.todo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val todoId: Long = 0,
    val title: String,
    val date: String,
    val priority: Boolean = false,
    val done: Boolean = false
)