package com.example.fitness_first

import CategoryScreen
import FavouritesScreen
import HomeScreen
import SearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitness_first.ui.components.BottomBarScreen
import com.example.fitness_first.ui.screens.*


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "landing",
) {
    val uri = "http://fitness-first.com"
    val secureUri = "https://fitness-first.com"

    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
//          - = - = - App screens go here - = - = -
        composable(route = "landing"){
            LandingScreen(
                { navController.navigate("register") },
                { navController.navigate("login") },
            )
        }

        composable(route = "login"){
            LoginScreen(
                { navController.navigate("landing") },
                { navController.navigate("home") },
            )
        }

        composable(route = "register"){
            RegisterScreen(
                { navController.navigate("landing") },
                { navController.navigate("home") },
            )
        }

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(
                NavigateToCategoryScreen = { route -> navController.navigate("category/$route")},
                navController
            )
        }
        composable(
            route = "category/{route}",
            arguments = listOf(navArgument("route") { type = NavType.StringType})
        ) {
            NavBackStackEntry ->
            CategoryScreen(
                NavBackStackEntry.arguments?.getString("route").toString(),
                navController
            )
        }
        composable(
            route = "search/{route}",
            arguments = listOf(navArgument("route") { type = NavType.StringType})
        ){
            NavBackStackEntry ->
            SearchScreen(
                NavBackStackEntry.arguments?.getString("route").toString(),
                navController
            )
        }

        composable(route = BottomBarScreen.Favourites.route){
            FavouritesScreen(
                navController
            )
        }

        composable(route = BottomBarScreen.Routines.route){
            /* TODO: Esto es provisorio, sacar!!! */
            val data = listOf(
                BasicRoutineData("hola", "chau", true),
            )
            MyRoutinesScreen(
                routineData = data,
                navController = navController,
                NavigateToRoutineDetails = { route -> navController.navigate("routine/$route")}
            )
        }

        composable(
            route = "routine/{title}",
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/routine/{title}" }, navDeepLink { uriPattern = "$secureUri/routine/{title}" }),
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) {
            NavBackStackEntry -> RoutineDetailsScreen(NavBackStackEntry.arguments?.getString("title").toString())
        }

        composable(
            route = "routine/{title}/execution",
            arguments = listOf(navArgument("title") { type = NavType.StringType }),
        ){
            NavBackStackEntry -> ExecutionScreen(NavBackStackEntry.arguments?.getString("title").toString())
        }

        composable(route = "profile"){
            MyProfileScreen()
        }

        composable(route = "settings"){
            SettingsScreen()
        }

        composable(route = "help"){
            HelpScreen()
        }



        composable("test"){
            TestScreen()
        }
    }
}