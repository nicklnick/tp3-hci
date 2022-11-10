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
    val icon: ImageVector
){
    object Bicep: Categories(
        route = "Bicep",
        title = "Bicep",
//        icon = painterResource(R.drawable.muscle)
        icon = Icons.Filled.Person
    )
    object Tricep: Categories(
        route = "Triceps",
//        title = stringResource(R.string.tricep),
        title = "Triceps",
        icon = Icons.Filled.Person
    )
    object Chest: Categories(
        route = "Chest",
        title = "Chest",
        icon = Icons.Filled.Person
    )
    object Shoulders: Categories(
        route = "Shoulders",
//        title = stringResource(R.string.bicep),
        title = "Shoulders",
        icon = Icons.Filled.Person
    )
    object Back: Categories(
        route = "Back",
        title = "Back",
        icon = Icons.Filled.Person
    )
    object Legs: Categories(
        route = "Legs",
        title = "Legs",
        icon = Icons.Filled.Person
    )
    object Abs: Categories(
        route = "Abs",
        title = "Abs",
        icon = Icons.Filled.Person
    )
    object FullBody: Categories(
        route = "Full body",
        title = "Full body",
        icon = Icons.Filled.Person
    )
}
