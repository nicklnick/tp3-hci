package com.example.fitness_first.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.components.DetailedRoutineButton
import com.example.fitness_first.ui.theme.FitnessfirstTheme
import com.example.fitness_first.ui.theme.Secondary

class BasicRoutineData
constructor(name: String, category: String, liked: Boolean, func: () -> Unit = {}) {
    val name = name
    val category = category
    val liked = liked
    val func = func
}

@Composable
fun MyRoutinesScreen(routineData: List<BasicRoutineData>) {
    FitnessfirstTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "My Routines",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
                color = Secondary,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                items(routineData) {
                    /* TODO: aca deberias poder hacer detailedRoutineButton(data) */
                    data -> DetailedRoutineButton(
                        name = data.name,
                        category = data.category,
                        liked = data.liked,
                        func = {},
                    ) { }
                }
            }
        }
    }
}

@Composable
fun TopBarMyRoutines() {
    TopAppBar(
        backgroundColor = Color.Transparent,
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = null,
                tint = Secondary
            )
        }
    }
}