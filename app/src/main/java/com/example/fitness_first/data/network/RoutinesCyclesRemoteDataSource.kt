package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiRoutinesCyclesService
import com.example.fitness_first.data.network.model.NetworkFullCycle
import com.example.fitness_first.data.network.model.NetworkPagedContent

class RoutinesCyclesRemoteDataSource (
    private val routinesCyclesService: ApiRoutinesCyclesService
) : RemoteDataSource() {

    suspend fun getRoutinesCycles(routineId: Int) : NetworkPagedContent<NetworkFullCycle> {
        return handleApiResponse {
            routinesCyclesService.getRoutinesCycles(routineId)
        }
    }
}