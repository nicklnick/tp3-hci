package com.example.fitness_first.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.fitness_first.R

sealed class Categories(
    val route: String,
    val title: String,
    val icon: Int
){
    object Bicep: Categories(
        route = "Bicep",
        title = "Bicep",
        icon = R.drawable.muscle
    )
    object Tricep: Categories(
        route = "Triceps",
        title ="Triceps",
        icon = R.drawable.triceps
    )
    object Chest: Categories(
        route = "Chest",
        title ="Chest",
        icon = R.drawable.torso
    )
    object Shoulders: Categories(
        route = "Shoulders",
        title ="Shoulders",
        icon = R.drawable.shoulders
    )
    object Back: Categories(
        route = "Back",
        title = "Back",
        icon = R.drawable.bodypart
    )
    object Legs: Categories(
        route = "Legs",
        title = "Legs",
        icon = R.drawable.piernas
    )
    object Abs: Categories(
        route = "Abs",
        title = "Abs",
        icon = R.drawable.abdominals
    )
    object FullBody: Categories(
        route = "Full body",
        title = "Full body",
        icon = R.drawable.full_body
    )
}
