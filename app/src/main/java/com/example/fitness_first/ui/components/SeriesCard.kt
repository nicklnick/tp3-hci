package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitness_first.data.model.FullCycleExercise
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

val rowHeight = 30.dp

@Composable
fun SeriesCard(title: String, repetitions: Int, cycleExerciseList: List<FullCycleExercise>) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(rowHeight + rowHeight * cycleExerciseList.size),
        backgroundColor = Quaternary,
        border = BorderStroke(1.dp, Secondary),
        shape = RoundedCornerShape(15)
    ) {
        Column {
            Surface(
                shape = RoundedCornerShape(10),
                color = Tertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(rowHeight)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier)

                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )

                    Surface(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(30.dp)
                            .padding(2.dp),
                        shape = RoundedCornerShape(30),
                        color = Color(
                            green = Secondary.green,
                            red = Secondary.red,
                            blue = Secondary.blue,
                            alpha = 0.6f
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "x${repetitions}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Surface(
                color = Quaternary,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    for (fullCycleExercise in cycleExerciseList)
                        ExerciseDetailRow(fullCycleExercise = fullCycleExercise)
                }
            }
        }
    }
}

@Composable
private fun ExerciseDetailRow(fullCycleExercise: FullCycleExercise) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(rowHeight)
            .background(Quaternary)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.5F)
                .height(rowHeight),
            color = Secondary.copy(alpha = 0.2f),
            border = BorderStroke(1.dp, Secondary)
        ) {
            Text(fullCycleExercise.exercise.name, textAlign = TextAlign.Center)
        }
        Surface(
            color = Quaternary,
            modifier = Modifier
                .fillMaxWidth()
                .height(rowHeight),
            border = BorderStroke(1.dp, Secondary)
        ) {
            var detail = fullCycleExercise.repetitions
            if (detail == 0) {
                detail = fullCycleExercise.duration
                Text(
                    text = detail.toString() + "s",
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(
                    text = detail.toString() + " reps",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
