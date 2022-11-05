package com.example.fitness_first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitness_first.ui.components.ExerciseDetail
import com.example.fitness_first.ui.components.SeriesCard
import com.example.fitness_first.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessfirstTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SeriesCard("Lazy Monday", listOf(ExerciseDetail("Brazos", "2"), ExerciseDetail("Piernas", "3"), ExerciseDetail("cabeza", "5")))
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitnessfirstTheme {
        SeriesCard("Lazy Monday", listOf(ExerciseDetail("Brazos", "2"), ExerciseDetail("Piernas", "3"), ExerciseDetail("cabeza", "5")))
    }
}