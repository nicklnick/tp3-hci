package com.example.fitness_first.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitness_first.ui.theme.Quaternary
import com.example.fitness_first.ui.theme.Secondary
import com.example.fitness_first.ui.theme.Tertiary

class ExerciseDetail constructor(exercise: String, detail: String) {
    val exercise = exercise
    val detail = detail
}

val rowHeight = 20.dp

@Composable
fun SeriesCard(title: String, exerciseDetailList: List<ExerciseDetail>) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(rowHeight + rowHeight * exerciseDetailList.size),
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = title,
                    )
                }
            }
            Surface(
                color = Quaternary,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.Red)
                ) {
                    for(exerciseDetail in exerciseDetailList)
                        ExerciseDetailRow(exerciseDetail = exerciseDetail)
                }
            }
        }
    }
}

@Composable
private fun ExerciseDetailRow(exerciseDetail: ExerciseDetail) {
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
            Text(exerciseDetail.exercise, textAlign = TextAlign.Center)
        }
        Surface(
            color = Quaternary,
            modifier = Modifier
                .fillMaxWidth()
                .height(rowHeight),
            border = BorderStroke(1.dp, Secondary)
        ) {
            Text(
                exerciseDetail.detail,
                textAlign = TextAlign.Center,
            )
        }
    }
}
