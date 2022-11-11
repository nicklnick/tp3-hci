package com.example.fitness_first.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.components.IconFAB
import com.example.fitness_first.ui.components.RatingBar
import com.example.fitness_first.ui.components.SimpleChip
import com.example.fitness_first.ui.theme.Primary
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

@Composable
fun RoutineDetailsScreen(title: String) {
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxHeight()
    ) {
        Scaffold(
            topBar = {TopBarRoutineDetails(title, 3.5f, 30)},
            modifier = Modifier.background(Secondary)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(Secondary)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    /**
                     * TODO: Aca tendriamos que poner las series card list de la rutina que clickearon
                     */
                }
            }
        }
    }
}


@Composable
fun TopBarRoutineDetails(title: String, difficulty: Float, duration: Int) {
    Column(
        modifier = Modifier.background(Secondary)
    ){
        Spacer(modifier = Modifier.padding(top = 12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SimpleChip("Difficulty: $difficulty", Tertiary)
            SimpleChip("$duration'", Tertiary)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconFAB(
                icon = Icons.Default.Share,
                func = { shareRoutine() },
                modifier = Modifier,
                Quaternary,
                Primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            IconFAB(
                icon = Icons.Default.Favorite,
                func = { favRoutine() },
                modifier = Modifier,
                Quaternary,
                Primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar()
        }
        Spacer(modifier = Modifier.padding(bottom = 4.dp))
    }
}

private fun shareRoutine() {
    // TODO: hacer!
}

private fun favRoutine() {
    // TODO: hacer!
}