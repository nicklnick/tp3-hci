package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiUserService
import com.example.fitness_first.data.network.model.NetworkCredentials
import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkRoutine
import com.example.fitness_first.data.network.model.NetworkUser
import com.example.fitness_first.util.SessionManager

class UserRemoteDataSource(
    private val apiUserService: ApiUserService,
    private val sessionManager: SessionManager
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser() : NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }

    suspend fun getCurrentUserRoutines() : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiUserService.getCurrentUserRoutines()
        }
    }
}