package com.example.todo.presentation.screens.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = hiltViewModel<MainViewModel>()
    val dayState by viewModel.dayState.collectAsState()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 55.dp, start = 60.dp, end = 60.dp),
            text = "Мотивация на этот день:",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
        )

        Text(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp),
            text = dayState.motivation,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )

    }

}