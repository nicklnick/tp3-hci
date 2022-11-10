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
    val title: Int,
    val icon: Int
){
    object Bicep: Categories(
        route = "Bicep",
        title = R.string.bicep,
        icon = R.drawable.muscle
//        icon = Icons.Filled.Person
    )
    object Tricep: Categories(
        route = "Triceps",
//        title = stringResource(R.string.tricep),
        title = R.string.tricep,
        icon = R.drawable.triceps
    )
    object Chest: Categories(
        route = "Chest",
        title = R.string.chest,
        icon = R.drawable.torso
    )
    object Shoulders: Categories(
        route = "Shoulders",
//        title = stringResource(R.string.bicep),
        title = R.string.shoulders,
        icon = R.drawable.shoulders
    )
    object Back: Categories(
        route = "Back",
        title = R.string.back,
        icon = R.drawable.bodypart
    )
    object Legs: Categories(
        route = "Legs",
        title = R.string.legs,
        icon = R.drawable.piernas
    )
    object Abs: Categories(
        route = "Abs",
        title = R.string.abs,
        icon = R.drawable.abdominals
    )
    object FullBody: Categories(
        route = "Full body",
        title = R.string.full_body,
        icon = R.drawable.full_body
    )
}
