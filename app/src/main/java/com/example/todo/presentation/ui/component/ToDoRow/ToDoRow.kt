package com.example.todo.presentation.ui.component.ToDoRow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.R
import com.example.todo.data.local.model.ToDo

@Composable
fun ToDoRow(toDo: ToDo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)


    ) {
        Image(
            painter = painterResource(id = if (toDo.done) R.drawable.done else R.drawable.notdone),
            contentDescription = "isDone"
        )

        Text(
            text = toDo.title,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )

        if (toDo.priority) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Избранное",
                modifier = Modifier.padding(end = 30.dp)
            )
        }



    }
}