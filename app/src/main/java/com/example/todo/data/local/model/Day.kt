package com.example.todo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day")
data class Day (
    @PrimaryKey(autoGenerate = false)
    val date: String = "",
    val motivation: String = "",
    val priorityToDos: List<ToDo> = emptyList(),
    val toDos: List<ToDo> = emptyList()

)