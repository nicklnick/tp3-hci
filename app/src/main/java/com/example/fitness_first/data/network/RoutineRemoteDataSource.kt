package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiRoutineService
import com.example.fitness_first.data.network.model.NetworkPagedContent
import com.example.fitness_first.data.network.model.NetworkRoutine

class RoutineRemoteDataSource (
    private val apiRoutineService : ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines(page: Int) : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getRoutines(page)
        }
    }

    suspend fun getRoutinesWFilter(order: String, dir: String) : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getRoutinesWFilter(order,dir)
        }
    }


    suspend fun getRoutine(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }

}