package com.example.todo.domain.usecase

import java.util.Calendar

class GetCurrentDateUseCase {
    fun invoke(): String {
        val calendar = Calendar.getInstance()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // Важно: значения месяца начинаются с 0, поэтому нужно добавить 1
        val year = calendar.get(Calendar.YEAR)
        return "$dayOfMonth-$month-$year"
    }
}