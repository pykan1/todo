package com.example.todo.presentation.ui.component.ListItem

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo.data.local.model.ListTask
import com.example.todo.presentation.screens.List.ListViewModel
import com.example.todo.presentation.ui.component.Add.AddItem
import com.example.todo.presentation.ui.component.ToDoRow.ToDoRow
import com.example.todo.presentation.ui.theme.BasicBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemToDo(navController: NavController, listViewModel: ListViewModel = viewModel()) {
    val listItemViewModel = hiltViewModel<ListItemViewModel>()
    val item = listViewModel.itemList.collectAsState().value
    val toDos = listItemViewModel.toDos.observeAsState(listOf()).value
    val localFocusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
                listItemViewModel.isAdd = false
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(BasicBox),
                onClick = { listItemViewModel.changeIsAdd() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "add"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Список\n${item.name}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
            )
            if (listItemViewModel.isAdd) {
                AddItem(listItemViewModel = listItemViewModel)
            }
            LazyColumn(modifier = Modifier.padding(7.dp)) {
                itemsIndexed(
                    toDos
                ) { _, item ->
                    ToDoRow(toDo = item)
                }
            }

        }
    }
}