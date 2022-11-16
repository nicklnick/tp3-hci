package com.example.fitness_first.ui.components

import com.example.fitness_first.R

sealed class Categories(
    val route: String,
    val title: String,
    val icon: Int,
    var id: Int? = null
){
    object Bicep: Categories(
        route = "Bicep",
        title = "Bicep",
        icon = R.drawable.muscle,
        id = 1
    )
    object Tricep: Categories(
        route = "Triceps",
        title ="Triceps",
        icon = R.drawable.triceps,
        id = 2
    )
    object Chest: Categories(
        route = "Chest",
        title ="Chest",
        icon = R.drawable.torso,
        id = 3
    )
    object Shoulders: Categories(
        route = "Shoulders",
        title ="Shoulders",
        icon = R.drawable.shoulders,
        id = 4
    )
    object Back: Categories(
        route = "Back",
        title = "Back",
        icon = R.drawable.bodypart,
        id = 5
    )
    object Legs: Categories(
        route = "Legs",
        title = "Legs",
        icon = R.drawable.piernas,
        id = 6
    )
    object Abs: Categories(
        route = "Abs",
        title = "Abs",
        icon = R.drawable.abdominals,
        id = 7
    )
    object FullBody: Categories(
        route = "Full body",
        title = "Full body",
        icon = R.drawable.full_body,
        id = 8
    )
}
