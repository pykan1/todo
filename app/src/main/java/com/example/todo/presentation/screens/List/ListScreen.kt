package com.example.todo.presentation.screens.List

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.data.local.model.ListTask
import com.example.todo.presentation.Settings.SettingsViewModel
import com.example.todo.presentation.ui.theme.Container
import com.example.todo.presentation.ui.theme.DarkPurple

@Composable
fun ListScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    val viewModel = hiltViewModel<ListViewModel>()
    val tasks = viewModel.listTask.observeAsState(listOf()).value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            text = "Списки",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
        )
        Button(
            onClick = {
                viewModel.changeIsAdd()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Container,
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                text = "Создать список",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
        if (viewModel.isAdd) {
            AddList(viewModel = viewModel, navController)
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(
                tasks
            ) {_, item ->
                ListItem(item = item, viewModel = viewModel, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddList(viewModel: ListViewModel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
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
                placeholder = { Text(text = "Введите название списка...") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                ),
                textStyle = TextStyle(fontSize = 18.sp),
                singleLine = true,
                modifier = Modifier.width(280.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.done),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    viewModel.changeIsAdd()
                    viewModel.createList()
                }
                .padding(5.dp)
                .size(65.dp)
        )
    }
}

@Composable
fun ListItem(item: ListTask, viewModel: ListViewModel, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .clickable {
                    viewModel.postItem(item, navController)
                },
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                modifier = Modifier.size(45.dp),
                painter = painterResource(id = R.drawable.plan),
                contentDescription = ""
            )
            Text(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
                text = item.name,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,)
        }
}