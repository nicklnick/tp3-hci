package com.example.fitness_first

import com.example.fitness_first.data.model.*
import com.example.fitness_first.ui.components.FilterOptions
import com.example.fitness_first.ui.components.NavItem

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val message: String? = null,

    //  - - - SETTINGS - - -
    val bottomBarItems: List<NavItem> =listOf(
        NavItem.Home,
        NavItem.Favourites,
        NavItem.Routines
    ),
    val bottomBarSelected: Int = 1,

    //  - - - USER - - -
    val currentUser: User? = null,

    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,

    //  - - - EXERCISES - - -
    val exercises: List<Exercise>? = null,
    val currentExercise: Exercise? = null,

    //  - - - CATEGORIES - - -
    val categories: List<Category>? = null,
    val currentCategory: Category? = null,

    //  - - - ROUTINES - - -
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
    val routinesCycles: List<CompleteCycle> = emptyList(),
    val cycleExercises: List<CompleteCycleExercise> = emptyList(),
    var cycleDataList: List<CycleData> = emptyList(),
    val favouriteRoutines: List<Routine>? = null,

    //   - - - REVIEWS - - -
    val reviews: List<Review>? = null,
    val currentReview: Review? = null,

    //  - - - EXECUTION - - -
    val currentExecExercise: CompleteCycleExercise? = null,
    val currentExecSeries: CycleData? = null,
    val currentExecSeriesIdx: Int= 0,
    val currentExecExerciseIdx: Int = 0,
    val nextExecExercise: CompleteCycleExercise? = null,

    val routineSize: Int = 1,
    val exerciseCount: Int = 0,
    val seriesRepCount: Int = 1,

    val execFinished: Boolean = false,

    val currentTimeExercise: Int = 0,
    val pausedExec: Boolean = false,
)


