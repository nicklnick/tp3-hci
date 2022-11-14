package com.example.fitness_first.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.data.model.Review

@Composable
fun SettingsScreen(viewModel: MainViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column() {
            Button(onClick = { viewModel.getCategories() }) {
                Text("get")
            }
//            Button(onClick = { viewModel.addReview(1, Review(4)) }) {
//                Text("put")
//            }
            (if(viewModel.uiState.message != null) viewModel.uiState.message else "No errors")?.let {
                Text(
                    it
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(viewModel.uiState.categories.orEmpty()) { item ->
                    Text(String.format("id: %d, name: %s", item.id, item.name))
                }
            }
        }
    }
}