package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.Routine
import com.example.fitness_first.data.model.User
import com.example.fitness_first.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null
    private var routines: List<Routine> = emptyList()

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun getCurrentUser(refresh: Boolean) : User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            // Thread-safe write to latestNews
            currentUserMutex.withLock {
                this.currentUser = result.asModel()
            }
        }

        return currentUserMutex.withLock { this.currentUser }
    }

    suspend fun getCurrentUserRoutines(): List<Routine>{
        val userRoutines = remoteDataSource.getCurrentUserRoutines()
        currentUserMutex.withLock {
            this.routines = userRoutines.content.map { it.asModel() }
        }
        return currentUserMutex.withLock { this.routines }
    }
}

