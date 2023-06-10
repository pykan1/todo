package com.example.todo.presentation.ui.component.ListItem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.data.local.model.ListTask
import com.example.todo.data.local.model.ToDo
import com.example.todo.domain.usecase.ChangeToDosListTaskUseCase
import com.example.todo.domain.usecase.GetListTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemViewModel @Inject constructor(
    private val changeToDosListTaskUseCase: ChangeToDosListTaskUseCase,
    private val getListTaskByIdUseCase: GetListTaskByIdUseCase
) : ViewModel() {
    val toDos = MutableLiveData<List<ToDo>>()
    var isAdd by mutableStateOf(false)
    var id: Long by mutableStateOf(-1)

    fun invoke(idTask: Long) {
        id = idTask
        viewModelScope.launch {
            getListTaskByIdUseCase.invoke(idTask).let {
                toDos.postValue(it.toDos)
            }
        }
    }

    fun changeIsAdd() {
        isAdd = !isAdd
    }

    fun addToDo(title: String) {
        viewModelScope.launch {
            getListTaskByIdUseCase.invoke(id).let {
                val newToDOs = it.toDos + ToDo(title = title)
                changeToDosListTaskUseCase.invoke(newToDOs, id)
                toDos.postValue(newToDOs)
            }
        }
    }
}