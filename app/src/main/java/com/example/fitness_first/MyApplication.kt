package com.example.fitness_first

import android.app.Application
import com.example.fitness_first.data.network.ExerciseRemoteDataSource
import com.example.fitness_first.data.network.SportRemoteDataSource
import com.example.fitness_first.data.network.UserRemoteDataSource
import com.example.fitness_first.data.network.api.RetrofitClient
import com.example.fitness_first.data.repository.ExerciseRepository
import com.example.fitness_first.data.repository.SportRepository
import com.example.fitness_first.data.repository.UserRepository
import com.example.fitness_first.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val exerciseRemoteDataSource: ExerciseRemoteDataSource
        get() = ExerciseRemoteDataSource(RetrofitClient.getApiExerciseService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val exerciseRepository: ExerciseRepository
        get() = ExerciseRepository(exerciseRemoteDataSource)
}