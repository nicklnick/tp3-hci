package com.example.fitness_first

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_first.data.model.Category
import com.example.fitness_first.data.model.Review
import com.example.fitness_first.data.model.Sport
import com.example.fitness_first.data.repository.*
import com.example.fitness_first.util.SessionManager
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository,
    private val reviewRepository: ReviewRepository,
    private val categoryRepository: CategoryRepository,
    private val favouriteRepository: FavouriteRepository,
    private val routinesCyclesRepository: RoutinesCyclesRepository,
    private val cyclesExercisesRepository: CyclesExercisesRepository
    ) : ViewModel() {

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    init {
        getRoutines()
        getFavourites()
    }

    fun login(username: String, password: String, successFunc: () -> Unit, failureFunc: suspend () -> Unit) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.login(username, password)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = true
            )
            successFunc()          // TODO: check!?
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
            failureFunc()
        }
    }

    fun logout(successFunc: () -> Unit) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.logout()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = false,
                currentUser = null,
                currentSport = null,
                sports = null
            )
            successFunc()
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.getCurrentUser(uiState.currentUser == null)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCurrentUserRoutines() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.getCurrentUserRoutines()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                userRoutines = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getSports() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.getSports(true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                sports = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getSport(sportId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.getSport(sportId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun addOrModifySport(sport: Sport) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            if (sport.id == null)
                sportRepository.addSport(sport)
            else
                sportRepository.modifySport(sport)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = response,
                sports = null
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun deleteSport(sportId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.deleteSport(sportId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = null,
                sports = null
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getExercises() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            exerciseRepository.getExercises(true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                exercises = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getExercise(exerciseId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            exerciseRepository.getExercise(exerciseId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentExercise = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getRoutines() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutines(true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getRoutinesWName(query: String) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutinesWName(query)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                searchRoutines = response
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getRoutinesWFilter(order: String = "name", dir: String = "asc") = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutinesWFilter(order, dir)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response,
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getReviews(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            reviewRepository.getReviews(routineId, true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                reviews = response
            )
        }.onFailure { e ->
        uiState = uiState.copy(
            message = e.message,
            isFetching = false
            )
        }
    }

    fun addReview(routineId:Int, review: Review) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            getReviews(routineId).join()
            getCurrentUser().join()

            // check that routine hasn't already been reviewed by current user
            for(currReview in uiState.reviews!!){
                if(currReview.userId == (uiState.currentUser?.id ?: 0)){
                    throw java.lang.RuntimeException("You have already reviewed this routine")
                }
            }
            reviewRepository.addReview(routineId, review)

        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCategories() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            categoryRepository.getCategories( true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                categories = response
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getCategory(categoryId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            categoryRepository.getCategory(categoryId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentCategory = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun addCategory(category: Category) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
                categoryRepository.addCategory(category)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentCategory = response,
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }
    fun getFavourites() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            favouriteRepository.getFavourites()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                favouriteRoutines = response
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun markFavourite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            favouriteRepository.markFavourite(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
            )
            getFavourites()
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }

    }

    fun deleteFavourite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            favouriteRepository.removeFavourite(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
            )
            getFavourites()
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getRoutinesCycles(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routinesCyclesRepository.getRoutineCycles(routineId = routineId, refresh = true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routinesCycles = response
            )
        }.onFailure {  e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getCycleExercises(cycleId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            cyclesExercisesRepository.getCycleExercises(cycleId = cycleId, refresh = true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                cycleExercises = response
            )
        }.onFailure {  e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getRoutineDetails(routineId: Int) = viewModelScope.launch {
        uiState.cycleDataList = emptyList()

        uiState = uiState.copy(
            isFetching = true,
            message = null
        )

        runCatching {
            getRoutinesCycles(routineId).join()

            for(cycle in uiState.routinesCycles!!) {
                getCycleExercises(cycle.id).join()

                uiState.cycleDataList = uiState.cycleDataList!!.plus(CycleData(cycle.name, cycle.repetitions, uiState.cycleExercises!!))
            }
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false
            )
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun getRoutine(id: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutine(id)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentRoutine = response
            )
            getRoutineDetails(id)
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }
}

