package com.example.fitness_first.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel

@Composable
fun topBar(
    menuFunc: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel,
    //NavigateToAllRoutinesScreen: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .height(128.dp)
            .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(0.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            UpperBar(menuFunc = menuFunc, navController = navController, viewModel = viewModel)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(navController, viewModel)
            }
        }
    }
}