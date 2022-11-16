package com.example.fitness_first.ui.components

import com.example.fitness_first.R

sealed class FilterOptions (
    val text: Int,
    val order: String,
    val dir: String
){
    object  DateUp: FilterOptions(
        text = R.string.date_up,
        order = "date",
        dir = "asc"
    )
    object  DateDown: FilterOptions(
        text = R.string.date_down,
        order = "date",
        dir = "desc"
    )
    object RatingUp: FilterOptions(
        text = R.string.rating_up,
        order = "score",
        dir = "asc"
    )
    object RatingDown: FilterOptions(
        text = R.string.rating_down,
        order = "score",
        dir = "desc"
    )
    object  DifficultyUp: FilterOptions(
        text = R.string.diff_up,
        order = "difficulty",
        dir = "asc"
    )
    object  DifficultyDown: FilterOptions(
        text = R.string.diff_down,
        order = "difficulty",
        dir = "desc"
    )
}