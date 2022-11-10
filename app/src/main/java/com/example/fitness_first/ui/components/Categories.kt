package com.example.fitness_first.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.fitness_first.R

sealed class Categories(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Bicep: Categories(
        route = "category/bicep",
        title = "Bicep",
        icon = Icons.Filled.Person
    )
    object Tricep: Categories(
        route = "category/tricep",
        title = "Tricep",
        icon = Icons.Filled.Person
    )
    object Chest: Categories(
        route = "category/chest",
        title = "Chest",
        icon = Icons.Filled.Person
    )
    object Shoulders: Categories(
        route = "category/shoulders",
//        title = stringResource(R.string.bicep),
        title = "Shoulders",
        icon = Icons.Filled.Person
    )
    object Back: Categories(
        route = "category/back",
        title = "Back",
        icon = Icons.Filled.Person
    )
    object Legs: Categories(
        route = "category/legs",
        title = "Legs",
        icon = Icons.Filled.Person
    )
    object Abs: Categories(
        route = "category/abs",
        title = "Abs",
        icon = Icons.Filled.Person
    )
    object FullBody: Categories(
        route = "category/fullbody",
        title = "Fullbody",
        icon = Icons.Filled.Person
    )
}
