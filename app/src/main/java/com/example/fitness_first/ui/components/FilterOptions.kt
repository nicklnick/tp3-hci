package com.example.fitness_first.ui.components

import androidx.compose.ui.res.stringResource
import com.example.fitness_first.R

sealed class FilterOptions (
    val text: Int,
    val order: String,
    val dir: String
){
    object  Date_Up: FilterOptions(
        text = R.string.date_up,
        order = "date",
        dir = "asc"
    )
    object  Date_Down: FilterOptions(
        text = R.string.date_down,
        order = "date",
        dir = "desc"
    )
    object Rating_Up: FilterOptions(
        text = R.string.rating_up,
        order = "score",
        dir = "asc"
    )
    object Rating_Down: FilterOptions(
        text = R.string.rating_down,
        order = "score",
        dir = "desc"
    )
    object  Difficulty_Up: FilterOptions(
        text = R.string.diff_up,
        order = "difficulty",
        dir = "asc"
    )
    object  Difficulty_Down: FilterOptions(
        text = R.string.diff_down,
        order = "difficulty",
        dir = "desc"
    )
}