package com.example.fitness_first

import HomeScreen
import MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.example.fitness_first.ui.screens.HomeScreen
import com.example.fitness_first.ui.screens.TestScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "test",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {

//          - = - = - App screens go here - = - = -

        composable("home") {
            HomeScreen()
        }

        composable("test"){
            TestScreen()
        }


    }
}