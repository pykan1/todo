package com.example.todo.presentation.screens.List

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.model.ListTask
import com.example.todo.data.local.model.ToDo
import com.example.todo.domain.usecase.ChangeToDosListTaskUseCase
import com.example.todo.domain.usecase.GetAllListTaskUseCase
import com.example.todo.domain.usecase.GetListTaskByIdUseCase
import com.example.todo.domain.usecase.InsertListTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAllListTaskUseCase: GetAllListTaskUseCase,
    private val insertListTaskUseCase: InsertListTaskUseCase,
    private val changeToDosListTaskUseCase: ChangeToDosListTaskUseCase,
    private val getListTaskByIdUseCase: GetListTaskByIdUseCase
) : ViewModel() {
    val listTask = MutableLiveData<List<ListTask>>()
    var isAdd by mutableStateOf(false)
    var title by mutableStateOf("")



    init {
        viewModelScope.launch {
            getAllListTaskUseCase.invoke().let {
                listTask.postValue(it)
                Log.d("11", it.toString())
            }
        }
    }

    fun setText(it: String) {
        title = it
    }

    fun changeIsAdd() {
        isAdd = !isAdd
    }

    fun createList() {
        viewModelScope.launch {
            val ls = ListTask(name = title)
            val newListTask = listTask.value?.plus(ls)
            insertListTaskUseCase.invoke(ls)
            listTask.postValue(newListTask)
            title = ""
        }
    }

    fun addToDo(title: String, id: Long) {
        viewModelScope.launch {
            getListTaskByIdUseCase.invoke(id).let {
                val newToDos = it.toDos + ToDo(title = title)
                changeToDosListTaskUseCase.invoke(newToDos, id = id)
                listTask.postValue(listTask.value?.map { it ->
                    if (it.id == id) {
                        ListTask(id = it.id, name = it.name, toDos = newToDos)
                    } else {
                        it
                    }
                })
            }
        }
    }
}