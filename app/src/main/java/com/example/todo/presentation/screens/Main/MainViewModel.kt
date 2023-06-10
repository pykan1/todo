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
import com.example.todo.domain.usecase.GetAllDayUseCase
import com.example.todo.domain.usecase.GetCurrentDateUseCase
import com.example.todo.domain.usecase.GetDayByDateUseCase
import com.example.todo.domain.usecase.InsertDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDayByDateUseCase: GetDayByDateUseCase,
    private val insertDayUseCase: InsertDayUseCase,
    private val changeToDosUseCase: ChangeToDosUseCase,
    private val getAllDayUseCase: GetAllDayUseCase
) : ViewModel() {
    private val currentDate = GetCurrentDateUseCase().invoke()
    var isAdd by mutableStateOf(false)
    var dayState = MutableStateFlow(Day())
    private val _toDos = MutableLiveData<List<ToDo>>()
    val toDos: LiveData<List<ToDo>>
        get() = _toDos


    fun initDay() {
        viewModelScope.launch {
            var day = getDayByDateUseCase.invoke(date = currentDate)
            if (day == null) {
                day = Day(
                    date = currentDate
                )
                dayState.emit(day)
                insertDayUseCase.invoke(day)
                _toDos.postValue(day.toDos)
                dayState.emit(day)
            } else {
                _toDos.postValue(day.toDos)
            }
        }
    }

    fun addToDo(title: String) {
        viewModelScope.launch {
            getDayByDateUseCase.invoke(currentDate).let {
                val newToDos = it.toDos + ToDo(title = title, date = dayState.value.date)
                changeToDosUseCase.invoke(newToDos, date = currentDate)
                _toDos.postValue(newToDos)
            }
        }
    }

    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch {
            //getDayByDateUseCase.invoke(date = currentDate)
            val newToDos = _toDos.value!!.map {
                if (it.title == toDo.title) {
                    toDo
                } else {
                    it
                }
            }
            changeToDosUseCase.invoke(newToDos, currentDate)
            _toDos.postValue(newToDos)
        }
    }

    fun changeIsAdd() {
        isAdd = !isAdd
    }


}