package com.example.fitness_first.ui.components

import com.example.fitness_first.R

class ClickableNavItem(
    val navItem: NavItem,
    val onClick: () -> Unit
)

/**
 * @property    name Strings resource id
 * @property    icon Drawable resource id
 */
sealed class NavItem(
    val route: String,
    val name: Int,
    val icon: Int,
) {
    object Home: NavItem(
        route = "home",
        name = R.string.nav_home,
        icon = R.drawable.logo_home
    )
    object Favourites: NavItem(
        route = "favourites",
        name = R.string.nav_favourites,
        icon = R.drawable.logo_favorites
    )
    object Routines: NavItem(
        route = "routines",
        name = R.string.nav_routines,
        icon = R.drawable.logo_routines
    )
    object MyProfile: NavItem(
        route = "profile",
        name = R.string.nav_profile,
        icon = R.drawable.logo_profile
    )
    object Settings: NavItem(
        route = "settings",
        name = R.string.nav_settings,
        icon = R.drawable.logo_settings
    )
    object Help: NavItem(
        route = "help",
        name = R.string.nav_help,
        icon = R.drawable.logo_help
    )
    object SignOut: NavItem(
        route = "landing",
        name = R.string.nav_signout,
        icon = R.drawable.logo_signout
    )
}