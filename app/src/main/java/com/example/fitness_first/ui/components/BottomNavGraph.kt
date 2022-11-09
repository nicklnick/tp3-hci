package com.example.fitness_first.ui.components

import HomeScreen
import RoutinesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(NavController: NavHostController){
    NavHost(
        navController = NavController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Routines.route){
            RoutinesScreen()
        }
    }
}