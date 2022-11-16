package com.example.fitness_first

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitness_first.data.model.Category
import com.example.fitness_first.data.model.Review
import com.example.fitness_first.data.model.Routine
import com.example.fitness_first.data.model.Sport
import com.example.fitness_first.data.repository.*
import com.example.fitness_first.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

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

    private var timer: Job? = null

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    init {
        if(uiState.isAuthenticated){
            setupViewModel()
        }
    }

    fun setupViewModel(){
        getRoutines()
        getCurrentUser()
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
            uiState.routines.orEmpty().forEach { routine ->
                if( uiState.userRoutines.orEmpty()
                        .find { it.id == routine.id } != null){
                    routine.fromCUser = true
                }
            }
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
            getFavourites()
            getCurrentUserRoutines()
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
            getFavourites()
            getCurrentUserRoutines()
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
            getReviews(uiState.currentRoutine!!.id)
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
            Log.d("amount of routines", uiState.routines!!.size.toString())
            Log.d("amount of liked", uiState.favouriteRoutines!!.size.toString())
            uiState.routines.orEmpty().forEach { routine ->
                if (uiState.favouriteRoutines.orEmpty()
                        .find { it.id == routine.id } != null
                ) {
                    routine.liked = true
                    Log.d("Routine liked", routine.liked.toString())
                }
            }
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
//            getFavourites()
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }

    fun likeOrUnlike(routine: Routine) = viewModelScope.launch {
        if( routine.liked){
            routine.liked = false
            deleteFavourite(routine.id)
        }else{
            routine.liked = true
            markFavourite(routine.id)
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
//            getFavourites()
            if( uiState.routines!!.find { it.id == id && it.liked == true} != null){
                uiState.currentRoutine!!.liked = true
            }
            getRoutineDetails(id).join()
            getReviews(id)
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
        }
    }


    fun setupExecution(){
        uiState = uiState.copy(currentExecSeriesIdx = 0)
        uiState = uiState.copy(currentExecExerciseIdx = 0)


        uiState = uiState.copy(currentExecSeries = uiState.cycleDataList[uiState.currentExecSeriesIdx])
        uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])


        peakNextExercise()
        uiState = uiState.copy(pausedExec = false)
        uiState = uiState.copy(execFinished = false)

        // Amount of exercise in routines
        uiState = uiState.copy(routineSize = 1)       // Asi nunca es 0
        for(cycle in uiState.cycleDataList){
            uiState = uiState.copy(routineSize = uiState.routineSize + cycle.cycleRepetitions * cycle.cycleExercises.size)
        }
        uiState = uiState.copy(routineSize = uiState.routineSize - 1)       // Asi nunca es 0
        uiState = uiState.copy(exerciseCount = 0)
        uiState = uiState.copy(seriesRepCount = 1)

        if(uiState.currentExecExercise!!.duration!! > 0) {
            uiState = uiState.copy(currentTimeExercise = uiState.currentExecExercise!!.duration!!)
            beginTimer()
        }
    }

    // - - - EXECUTION - - -

    fun isFirstExercise(): Boolean{
        return uiState.currentExecExerciseIdx == 0  && uiState.currentExecSeriesIdx == 0
    }
    fun hasNextExercise() : Boolean{
        return (uiState.currentExecExerciseIdx < uiState.currentExecSeries!!.cycleExercises.size - 1) ||
                (uiState.currentExecSeriesIdx < uiState.cycleDataList.size - 1) ||
                (uiState.seriesRepCount < uiState.currentExecSeries!!.cycleRepetitions)
        // asumo que si hay otro ciclo => hay mas ejercicios
    }

    fun peakNextExercise(){
        if(!hasNextExercise())
            return
        if(uiState.currentExecExerciseIdx < uiState.currentExecSeries!!.cycleExercises.size - 1){
            val auxiNextExer = uiState.currentExecExerciseIdx + 1
            uiState = uiState.copy(nextExecExercise = uiState.currentExecSeries!!.cycleExercises[auxiNextExer])
        }
        else{
            // Aun quedan repeticiones
            if(uiState.seriesRepCount < uiState.currentExecSeries!!.cycleRepetitions){
                val auxiNextExer = 0
                uiState = uiState.copy(nextExecExercise = uiState.currentExecSeries!!.cycleExercises[auxiNextExer])
            }
            // Aun quedan series y por ende, ejercicios
            else if(uiState.currentExecSeriesIdx < uiState.cycleDataList.size - 1){
                val auxiNextSeries = uiState.currentExecSeriesIdx + 1
                uiState = uiState.copy(nextExecExercise = uiState.cycleDataList[auxiNextSeries].cycleExercises[0])
            }
        }
    }

    fun nextExercise(){
        timer?.cancel()
        // Aun quedan ejercicios en la serie
        if(uiState.currentExecExerciseIdx < uiState.currentExecSeries!!.cycleExercises.size - 1){
            uiState = uiState.copy(currentExecExerciseIdx = uiState.currentExecExerciseIdx + 1)
            uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])
        }
        else{
            // Aun tengo que repetir esta serie devuelta
            if(uiState.seriesRepCount < uiState.currentExecSeries!!.cycleRepetitions){
                uiState = uiState.copy(currentExecExerciseIdx = 0)
                uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])
                uiState = uiState.copy(seriesRepCount = uiState.seriesRepCount + 1)
            }
            // Aun quedan series y por ende, ejercicios
            else if(uiState.currentExecSeriesIdx < uiState.cycleDataList.size - 1){
                uiState = uiState.copy(seriesRepCount =1)
                uiState = uiState.copy(currentExecSeriesIdx = uiState.currentExecSeriesIdx + 1)
                uiState = uiState.copy(currentExecSeries = uiState.cycleDataList[uiState.currentExecSeriesIdx])

                uiState = uiState.copy(currentExecExerciseIdx = 0)
                uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])
            }
            // No quedan ejercicios o series
            else{
                uiState = uiState.copy(execFinished = true)
                return
            }
        }
        if(uiState.currentExecExercise!!.duration!! > 0){
            beginTimer()
        }
        unpauseExecution()
        peakNextExercise()
        uiState = uiState.copy(exerciseCount = uiState.exerciseCount + 1)
    }

    fun previousExercise(){
        timer?.cancel()
        // Puedo volver para atras en la serie actual
        if(uiState.currentExecExerciseIdx > 0){
            uiState = uiState.copy(currentExecExerciseIdx = uiState.currentExecExerciseIdx - 1)
            uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])
        }
        else{
            // Todavia quedan repeticiones
            if(uiState.seriesRepCount > 1){
                uiState = uiState.copy(currentExecExerciseIdx = uiState.currentExecSeries!!.cycleExercises.size - 1)
                uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])
                uiState = uiState.copy(seriesRepCount = uiState.seriesRepCount - 1)
            }
            // Tengo que pasar a la serie anterior
            else if(uiState.currentExecSeriesIdx > 0){
                uiState = uiState.copy(seriesRepCount = 1)
                uiState = uiState.copy(currentExecSeriesIdx = uiState.currentExecSeriesIdx - 1)
                uiState = uiState.copy(currentExecSeries = uiState.cycleDataList[uiState.currentExecSeriesIdx])

                uiState = uiState.copy(currentExecExerciseIdx = uiState.currentExecSeries!!.cycleExercises.size - 1)
                uiState = uiState.copy(currentExecExercise = uiState.currentExecSeries!!.cycleExercises[uiState.currentExecExerciseIdx])
            }
        }
        if(uiState.currentExecExercise!!.duration!! > 0){
            beginTimer()
        }
        unpauseExecution()
        peakNextExercise()
        uiState = uiState.copy(exerciseCount = uiState.exerciseCount - 1)
    }

    fun beginTimer(){
        uiState = uiState.copy(currentTimeExercise = uiState.currentExecExercise!!.duration!!)

        timer?.cancel()
        timer = viewModelScope.launch {
            while(uiState.currentTimeExercise > 0){
                delay(1.seconds)
                if(!uiState.pausedExec)
                    uiState = uiState.copy(currentTimeExercise = uiState.currentTimeExercise - 1)
            }
            if(uiState.currentTimeExercise == 0)
                nextExercise()
        }

    }
    fun canPauseExecution(): Boolean{
        return timer == null || timer?.isActive == true
    }
    fun pauseExecution(){
        uiState = uiState.copy(pausedExec = true)
    }
    fun unpauseExecution(){
        uiState = uiState.copy(pausedExec = false)
    }

}

