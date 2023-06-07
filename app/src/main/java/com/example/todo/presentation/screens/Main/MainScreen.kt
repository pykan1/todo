package com.example.todo.presentation.screens.Main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.presentation.navigation.Screens
import com.example.todo.presentation.ui.component.ToDoRow.ToDoRow
import com.example.todo.presentation.ui.theme.BasicBox
import com.example.todo.presentation.ui.theme.DarkPurple
import com.example.todo.presentation.ui.theme.PriorityBox

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val dayState by viewModel.dayState.collectAsState()
    val localFocusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(BasicBox),
                onClick = { viewModel.changeIsAdd() }
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
                .background(Color.White)
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 60.dp, end = 60.dp),
                text = "Мотивация на этот день:",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
            )

            Text(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp)
                    .fillMaxWidth(),
                text = dayState.motivation,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .heightIn(min = 220.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BasicBox),
                contentAlignment = Alignment.Center
            ) {
                if (dayState.priorityToDos.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Сейчас тут пусто",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        itemsIndexed(
                            dayState.priorityToDos
                        ) { _, toDo ->
                            ToDoRow(toDo = toDo)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(30.dp))
            if (viewModel.isAdd) {
                AddItem(mainViewModel = viewModel)
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .heightIn(min = 220.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(PriorityBox),
                contentAlignment = Alignment.Center
            ) {
                if (dayState.toDos.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Сейчас тут пусто",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        itemsIndexed(
                            dayState.toDos
                        ) { _, toDo ->
                            ToDoRow(toDo = toDo)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItem(mainViewModel: MainViewModel) {
    val viewModel = hiltViewModel<AddViewModel>()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .heightIn(50.dp)
            .background(DarkPurple),
        horizontalArrangement = Arrangement.spacedBy(5.dp)

    ) {
        Card(
            modifier = Modifier
                .padding(start = 5.dp, end = 40.dp, bottom = 5.dp, top = 5.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            TextField(
                value = viewModel.title, {
                    viewModel.setText(it)
                },
                placeholder = { Text(text = "Введите задачу...") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = true,
                modifier = Modifier
            )
        }

        Image(
            painter = painterResource(id = R.drawable.done),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    mainViewModel.changeIsAdd()
                    viewModel.addToDo(mainViewModel)
                }
                .padding(5.dp)
                .size(65.dp)
        )

    }
}