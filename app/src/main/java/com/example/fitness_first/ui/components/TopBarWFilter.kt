package com.example.fitness_first.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun TopBarWFilter(
    onClickMenu:() -> Unit,
    onClickFilter: () -> Unit,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    TopAppBar(
        modifier = Modifier.height(128.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(0.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UpperBar(menuFunc = onClickMenu, navController = navController, viewModel = viewModel)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(navController, viewModel)
                IconFAB(
                    icon = Icons.Filled.List,
                    func = onClickFilter,
                    modifier = Modifier.size(50.dp),
                    backgroundColor = Tertiary,
                    contentColor = Color.Black
                )
            }
        }
    }
}
