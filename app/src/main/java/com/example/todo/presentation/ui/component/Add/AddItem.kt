package com.example.todo.presentation.ui.component.Add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.R
import com.example.todo.presentation.screens.Main.MainViewModel
import com.example.todo.presentation.ui.theme.DarkPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItem(mainViewModel: MainViewModel) {
    val viewModel = AddViewModel()
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
                modifier = Modifier.width(280.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.done),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    mainViewModel.changeIsAdd()
                    mainViewModel.addToDo(viewModel.title)
                }
                .padding(5.dp)
                .size(65.dp)
        )

    }
}