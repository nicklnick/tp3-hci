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
import com.example.fitness_first.ui.components.NavItem
import com.example.fitness_first.ui.screens.*
import com.example.fitness_first.util.getViewModelFactory


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "landing",
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
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
                { navController.navigate("home") },
                viewModel
            )
        }

        composable(route = "login"){
            LoginScreen(
                { navController.navigate("landing") },
                { navController.navigate("home") },
                viewModel
            )
        }

        composable(route = "register"){
            RegisterScreen(
                { navController.navigate("landing") },
                { navController.navigate("home") },
            )
        }

        composable(route = NavItem.Home.route){
            HomeScreen(
                NavigateToCategoryScreen = { route -> navController.navigate("category/$route") },
                navController,
                viewModel,
                NavigateToAllRoutinesScreen = { navController.navigate("allRoutines") }
            )
        }
        composable(
            route = "category/{route}",
            arguments = listOf(navArgument("route") { type = NavType.StringType })
        ) {
            NavBackStackEntry ->
            CategoryScreen(
                NavBackStackEntry.arguments?.getString("route").toString(),
                navController,
                viewModel,
                NavigateToRoutineDetails = { route -> navController.navigate("routine/$route")}
            )
        }
        composable(
            route = "search/{route}",
            arguments = listOf(navArgument("route") { type = NavType.StringType })
        ){
            NavBackStackEntry ->
            SearchScreen(
                NavBackStackEntry.arguments?.getString("route").toString(),
                navController,
                viewModel,
                NavigateToRoutineDetails = { route -> navController.navigate("routine/$route")}
            )
        }

        composable(route = NavItem.Favourites.route){
            FavouritesScreen(
                NavigateToRoutineDetails = { route -> navController.navigate("routine/$route")},
                navController,
                viewModel
            )
        }

        composable(route = NavItem.Routines.route){
            MyRoutinesScreen(
                navController = navController,
                NavigateToRoutineDetails = { route -> navController.navigate("routine/$route")},
                viewModel = viewModel
            )
        }

        composable(
            route = "routine/{id}",
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/routine/{id}" }, navDeepLink { uriPattern = "$secureUri/routine/{id}" }),
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            NavBackStackEntry -> RoutineDetailsScreen(
            NavBackStackEntry.arguments?.getInt("id")!!,
            {navController.navigate("routine/${NavBackStackEntry.arguments?.getInt("id")}/execution")},
            viewModel)
        }

        composable(
            route = "routine/{id}/execution",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ){
            NavBackStackEntry ->
            ExecutionScreen(
                NavBackStackEntry.arguments?.getInt("id")!!,
                {navController.navigate("routine/${NavBackStackEntry.arguments?.getInt("id")}")},
                {navController.navigate("routine/${NavBackStackEntry.arguments?.getInt("id")}/review")},
                viewModel
            )
        }

        composable(
            route = "routine/{id}/review",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ){ NavBackStackEntry ->
            RoutineReviewScreen(
                NavBackStackEntry.arguments?.getInt("id")!!,
                {navController.navigate("home")},
                viewModel
            )
        }

        composable(route = "profile"){
            MyProfileScreen(viewModel)
        }

        composable(route = "settings"){
            SettingsScreen(viewModel)
        }

        composable(route = "help"){
            HelpScreen()
        }

        composable("allRoutines"){
            AllRoutinesScreen(
                navController,
                viewModel,
                NavigateToRoutineDetails = { route -> navController.navigate("routine/$route")}
            )
        }



        composable("test"){
            TestScreen()
        }
    }
}