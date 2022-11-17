package com.example.fitness_first.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.R
import com.example.fitness_first.data.model.Review
import com.example.fitness_first.ui.theme.Tertiary
import java.util.stream.IntStream.range

@Composable
fun SettingsScreen(viewModel: MainViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            for(i in range(1,4)){
                Row() {
                    Text("Option :${i}")
                    RadioButton(
                        selected = viewModel.uiState.bottomBarSelected == i,
                        onClick = { viewModel.reorderBottomNav(i) }
                    )
                }
            }
        }
    }
}