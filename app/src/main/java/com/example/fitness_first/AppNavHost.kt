package com.example.fitness_first

import FavouritesScreen
import HomeScreen
import MainScreen
import RoutinesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitness_first.ui.components.BottomBarScreen
//import com.example.fitness_first.ui.screens.HomeScreen
import com.example.fitness_first.ui.screens.TestScreen
import com.example.fitness_first.ui.screens.*


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "landing",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {

//          - = - = - App screens go here - = - = -
        composable("landing"){
            LandingScreen(
                { navController.navigate("register") },
                { navController.navigate("login") },
            )
        }

        composable("login"){
            LoginScreen(
                { navController.navigate("landing") },
                { navController.navigate("home") },
            )
        }

        composable("register"){
            RegisterScreen(
                { navController.navigate("landing") },
                { navController.navigate("home") },
            )
        }

        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Favourites.route){
            FavouritesScreen()
        }
        composable(route = BottomBarScreen.Routines.route){
            RoutinesScreen()
        }

        composable("test"){
            TestScreen()
        }


    }
}