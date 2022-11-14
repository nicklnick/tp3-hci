package com.example.fitness_first

import android.app.Application
import com.example.fitness_first.data.network.*
import com.example.fitness_first.data.network.api.RetrofitClient
import com.example.fitness_first.data.repository.*
import com.example.fitness_first.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val exerciseRemoteDataSource: ExerciseRemoteDataSource
        get() = ExerciseRemoteDataSource(RetrofitClient.getApiExerciseService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    private val reviewRemoteDataSource: ReviewRemoteDataSource
        get() = ReviewRemoteDataSource(RetrofitClient.getApiReviewService(this))

    private val categoryRemoteDataSource: CategoryRemoteDataSource
        get() = CategoryRemoteDataSource(RetrofitClient.getApiCategoryService(this))

    private val favouriteRemoteDataSource: FavouriteRemoteDataSource
        get() = FavouriteRemoteDataSource(RetrofitClient.getApiFavouriteService(this))

    private val routinesCyclesRemoteDataSource: RoutinesCyclesRemoteDataSource
        get() = RoutinesCyclesRemoteDataSource(RetrofitClient.getApiRoutinesCyclesService(this))

    private val cyclesExercisesRemoteDataSource: CyclesExercisesRemoteDataSource
        get() = CyclesExercisesRemoteDataSource(RetrofitClient.getApiCyclesExercisesService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val exerciseRepository: ExerciseRepository
        get() = ExerciseRepository(exerciseRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)

    val reviewRepository: ReviewRepository
        get() = ReviewRepository(reviewRemoteDataSource)

    val categoryRepository: CategoryRepository
        get() = CategoryRepository(categoryRemoteDataSource)

    val favouriteRepository: FavouriteRepository
        get() = FavouriteRepository(favouriteRemoteDataSource)

    val routinesCyclesRepository: RoutinesCyclesRepository
        get() = RoutinesCyclesRepository(routinesCyclesRemoteDataSource)

    val cyclesExercisesRepository: CyclesExercisesRepository
        get() = CyclesExercisesRepository(cyclesExercisesRemoteDataSource)
}