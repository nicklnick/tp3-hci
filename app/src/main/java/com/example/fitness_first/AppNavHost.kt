package com.example.fitness_first

import CategoryScreen
import FavouritesScreen
import HomeScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitness_first.ui.components.BottomBarScreen
import com.example.fitness_first.ui.screens.*


@OptIn(ExperimentalMaterialApi::class)
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
            HomeScreen(
                NavigateToCategoryScreen = { route -> navController.navigate("category/$route")}
            )
        }
        composable(
            "category/{route}",
            arguments = listOf(navArgument("route") { type = NavType.StringType})
        ) {
            NavBackStackEntry ->
            CategoryScreen(NavBackStackEntry.arguments?.getString("route").toString())
        }

        composable(route = BottomBarScreen.Favourites.route){
            FavouritesScreen()
        }

        composable(route = BottomBarScreen.Routines.route){
            /* TODO: Esto es provisorio, sacar!!! */
            val data = listOf(
                BasicRoutineData("hola", "chau", true),
            )
            MyRoutinesScreen(routineData = data)
        }

        composable(
            route = "routineDetails/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) {
            NavBackStackEntry ->
            RoutineDetailsScreen(NavBackStackEntry.arguments?.getString("title").toString())
        }




        composable("execution"){
            ExecutionScreen()
        }


        composable("test"){
            TestScreen()
        }
    }
}