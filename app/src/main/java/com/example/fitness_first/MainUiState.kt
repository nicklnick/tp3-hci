package com.example.fitness_first

import com.example.fitness_first.data.model.Exercise
import com.example.fitness_first.data.model.Routine
import com.example.fitness_first.data.model.Sport
import com.example.fitness_first.data.model.User

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,

    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,

    val exercises: List<Exercise>? = null,
    val currentExercise: Exercise? = null,

    val message: String? = null,
    val routines: List<Routine>? = null,
    val searchRoutines: List<Routine>? = null,
    val currentRoutine: Routine? = null
)


val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport
