package com.example.fitness_first

import com.example.fitness_first.data.model.*
import com.example.fitness_first.ui.components.FilterOptions

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val message: String? = null,

    // USER
    val currentUser: User? = null,

    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,

    // EXERCISES
    val exercises: List<Exercise>? = null,
    val currentExercise: Exercise? = null,

    // CATEGORIES
    val categories: List<Category>? = null,
    val currentCategory: Category? = null,

    // ROUTINES
    val routines: List<Routine>? = null,
    val currentRoutine: Routine? = null,
    val orderBy: Int = 0,
    val filters: List<FilterOptions> = listOf(
        FilterOptions.DateUp,
        FilterOptions.DateDown,
        FilterOptions.RatingUp,
        FilterOptions.RatingDown,
        FilterOptions.DifficultyUp,
        FilterOptions.DifficultyDown,
    ),
    val userRoutines: List<Routine>? = null,
    val routinesCycles: List<FullCycle> = emptyList(),
    val cycleExercises: List<FullCycleExercise> = emptyList(),
    var cycleDataList: List<CycleData> = emptyList(),
    val favouriteRoutines: List<Routine>? = null,

    // REVIEWS
    val reviews: List<Review>? = null,
    val currentReview: Review? = null,

    //  - - - EXECUTION - - -
    val currentExecExercise: FullCycleExercise? = null,
    val currentExecSeries: CycleData? = null,
    val currentExecSeriesIdx: Int= 0,
    val currentExecExerciseIdx: Int = 0,
    val nextExecExercise: FullCycleExercise? = null,

    val routineSize: Int = 1,
    val exerciseCount: Int = 0,
    val seriesRepCount: Int = 1,

    val execFinished: Boolean = false,

    val currentTimeExercise: Int = 0,
    val pausedExec: Boolean = false,
)


val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport
val MainUiState.canGetRoutineCycles: Boolean get() = isAuthenticated



data class CycleData(
    val cycleName: String,
    val cycleRepetitions: Int,
    val cycleExercises: List<FullCycleExercise>
)