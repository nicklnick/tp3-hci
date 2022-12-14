package com.example.fitness_first.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.fitness_first.MainViewModel
import com.example.fitness_first.data.repository.*

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val settingsManager: SettingsManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository,
    private val reviewRepository: ReviewRepository,
    private val categoryRepository: CategoryRepository,
    private val favouriteRepository: FavouriteRepository,
    private val routinesCyclesRepository: RoutinesCyclesRepository,
    private val cyclesExercisesRepository: CyclesExercisesRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(sessionManager,settingsManager, userRepository, sportRepository, exerciseRepository,
                    routineRepository, reviewRepository, categoryRepository, favouriteRepository,
                routinesCyclesRepository, cyclesExercisesRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}