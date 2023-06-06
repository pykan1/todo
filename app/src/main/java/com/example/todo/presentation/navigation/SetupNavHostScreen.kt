package com.example.todo.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SetupNavHostScreen(navController: NavHostController) {
    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier.height(40.dp),
//                backgroundColor = topbar,
//                title = { Text(text = "") },
//                navigationIcon = run {
//                    {
//                        IconButton(onClick = { navController.navigateUp() }) {
//                            Icon(
//                                imageVector = Icons.Filled.ArrowBack,
//                                contentDescription = ""
//                            )
//                        }
//                    }
//                }
//            )
//        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                SetupNavHost(navController = navController)
            }
        }
    )
}