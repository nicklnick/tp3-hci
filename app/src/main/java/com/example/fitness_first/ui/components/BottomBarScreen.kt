package com.example.fitness_first.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Favourites: BottomBarScreen(
        route = "favourites",
        title = "Favourites",
        icon = Icons.Filled.Favorite
    )
    object Routines: BottomBarScreen(
        route = "routines",
        title = "Routines",
        icon = Icons.Filled.DateRange
    )
}
