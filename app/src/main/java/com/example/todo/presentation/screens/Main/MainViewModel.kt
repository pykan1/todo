package com.example.todo.presentation.screens.Main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Day
import com.example.todo.domain.usecase.GetDayByDateUseCase
import com.example.todo.domain.usecase.InsertDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDayByDateUseCase: GetDayByDateUseCase,
    private val insertDayUseCase: InsertDayUseCase
): ViewModel() {
    private val calendar = Calendar.getInstance()
    private val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    private val month = calendar.get(Calendar.MONTH) + 1 // Важно: значения месяца начинаются с 0, поэтому нужно добавить 1
    private val year = calendar.get(Calendar.YEAR)
    private val currentDate = "$dayOfMonth-$month-$year"
    var dayState = MutableStateFlow(Day())

    init {
        viewModelScope.launch {
            var day = getDayByDateUseCase.invoke(date = currentDate)
            if (day == null) {
                Log.d("11", "new day")
                day = Day(
                    date = currentDate
                )
                dayState.emit(day)
                insertDayUseCase.invoke(day)

            } else {
            dayState.emit(day)
            }
        }
    }



}