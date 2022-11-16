package com.example.fitness_first.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.fitness_first.R


/**
 * @property    name Strings resource id
 * @property    icon Drawable resource id
 */
sealed class Categories(
    val route: String,
    val name: Int,
    val icon: Int
) {
    @Composable
    fun getName() : String {
        return stringResource(name)
    }

    object Bicep: Categories(
        route = "Bicep",
        name = R.string.bicep,
        icon = R.drawable.muscle,
    )
    object Tricep: Categories(
        route = "Triceps",
        name = R.string.tricep,
        icon = R.drawable.triceps,
    )
    object Chest: Categories(
        route = "Chest",
        name = R.string.chest,
        icon = R.drawable.torso,
    )
    object Shoulders: Categories(
        route = "Shoulders",
        name = R.string.shoulders,
        icon = R.drawable.shoulders,
    )
    object Back: Categories(
        route = "Back",
        name = R.string.back,
        icon = R.drawable.bodypart,
    )
    object Legs: Categories(
        route = "Legs",
        name = R.string.legs,
        icon = R.drawable.piernas,
    )
    object Abs: Categories(
        route = "Abs",
        name = R.string.abs,
        icon = R.drawable.abdominals,
    )
    object FullBody: Categories(
        route = "Full body",
        name = R.string.full_body,
        icon = R.drawable.full_body,
    )
}
