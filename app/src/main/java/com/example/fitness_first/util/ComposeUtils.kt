package com.example.fitness_first.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.fitness_first.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val sportRepository = application.sportRepository
    val routineRepository = application.routineRepository
    val exerciseRepository = application.exerciseRepository
    val reviewRepository = application.reviewRepository
    val categoryRepository = application.categoryRepository
    val favouriteRepository = application.favouriteRepository
    return ViewModelFactory(sessionManager, userRepository, sportRepository, exerciseRepository,
        routineRepository, reviewRepository, categoryRepository, favouriteRepository,
        LocalSavedStateRegistryOwner.current, defaultArgs)

}