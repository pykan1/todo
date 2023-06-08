package com.example.todo.presentation.screens.Calendar

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Day
import com.example.todo.data.local.model.ToDo
import com.example.todo.domain.usecase.ChangeToDosUseCase
import com.example.todo.domain.usecase.GetAllDayUseCase
import com.example.todo.domain.usecase.GetCurrentDateUseCase
import com.example.todo.domain.usecase.GetDayByDateUseCase
import com.example.todo.domain.usecase.InsertDayUseCase
import com.example.todo.presentation.screens.Main.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getDayByDateUseCase: GetDayByDateUseCase,
    private val changeToDosUseCase: ChangeToDosUseCase,
    private val insertDayUseCase: InsertDayUseCase,
    private val getAllDayUseCase: GetAllDayUseCase,
    val mainViewModel: MainViewModel
) : ViewModel() {
    val calendar = Calendar.getInstance()
    var day by mutableStateOf(0)

    private val _toDos = MutableLiveData<List<ToDo>>()
    val toDos: LiveData<List<ToDo>>
        get() = _toDos


    init {
        Log.d("11", toDos.toString())
    }


    fun getDate(dayOfMonth: Int): String {
        return "$dayOfMonth-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.YEAR)}"
    }

    fun currentDay(dayOfMonth: Int) {
        day = dayOfMonth
        val date = getDate(dayOfMonth)
        Log.d("11", date)
        viewModelScope.launch {
            var day = getDayByDateUseCase.invoke(date)
            if (day == null) {
                Log.d("11", "new day")
                day = Day(
                    date = date
                )
                insertDayUseCase.invoke(day)
                _toDos.postValue(day.toDos)
            } else {
                _toDos.postValue(day.toDos)
            }
        }
    }
}