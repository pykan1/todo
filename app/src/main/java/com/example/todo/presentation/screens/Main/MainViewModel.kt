package com.example.todo.presentation.screens.Main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.Day
import com.example.todo.data.local.model.ToDo
import com.example.todo.domain.usecase.ChangeToDosUseCase
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
    private val insertDayUseCase: InsertDayUseCase,
    private val changeToDosUseCase: ChangeToDosUseCase,
): ViewModel() {
    private val calendar = Calendar.getInstance()
    private val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    private val month = calendar.get(Calendar.MONTH) + 1 // Важно: значения месяца начинаются с 0, поэтому нужно добавить 1
    private val year = calendar.get(Calendar.YEAR)
    private val currentDate = "$dayOfMonth-$month-$year"
    var isAdd by mutableStateOf(false)
    var dayState = MutableStateFlow(Day())
    private val _toDos = MutableLiveData<List<ToDo>>()
    private val _priority = MutableLiveData<List<ToDo>>()
    val toDos: LiveData<List<ToDo>>
        get() = _toDos

    val priority: LiveData<List<ToDo>>
        get() = _priority

    init {
        viewModelScope.launch {
            var day = getDayByDateUseCase.invoke(date = currentDate)
            if (day == null) {
                Log.d("11", "new day")
                day = Day(
                    date = currentDate
                )
                dayState.emit(day)
                _toDos.postValue(emptyList())
                _priority.postValue(emptyList())
                insertDayUseCase.invoke(day)

            } else {
                _toDos.postValue(day.toDos)
                _priority.postValue(day.priorityToDos)
            dayState.emit(day)
            }
        }
    }

    var title by mutableStateOf("")

    fun setText(text: String) {
        title = text
    }

    fun addToDo() {
        viewModelScope.launch {
            val toDo = ToDo(
                title = title,
                date = dayState.value.date
            )
            val newToDos = dayState.value.toDos + toDo
            dayState.emit(
                Day(
                    date = dayState.value.date,
                    motivation = dayState.value.date,
                    priorityToDos = dayState.value.priorityToDos,
                    toDos = newToDos
                )
            )
            changeToDosUseCase.invoke(newToDos, dayState.value.date)
        }
    }

    fun changeIsAdd() {
        isAdd = !isAdd
    }


}