package com.example.fitness_first.data.repository

import androidx.compose.ui.text.toLowerCase
import com.example.fitness_first.data.model.Routine
import com.example.fitness_first.data.network.RoutineRemoteDataSource
import com.example.fitness_first.ui.components.FilterOptions
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository (
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private val routinesMutex = Mutex()
    private var routines: List<Routine> = emptyList()

    suspend fun getRoutines(refresh: Boolean = false): List<Routine> {
        if( refresh || routines.isEmpty()){
            val result = remoteDataSource.getRoutines()
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }
        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutinesWName(query: String): List<Routine> {
        val allRoutines = remoteDataSource.getRoutines()
        routinesMutex.withLock {
            this.routines = allRoutines.content.filter{ it.name.toLowerCase().equals(query.toLowerCase())}.map { it.asModel() }
        }
        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutinesWFilter(order: String, dir: String): List<Routine> {
        val filteredRoutines = remoteDataSource.getRoutinesWFilter(order, dir)
        routinesMutex.withLock {
            this.routines = filteredRoutines.content.map { it.asModel() }
        }
        return routinesMutex.withLock { this.routines }
    }

//    suspend fun getRoutinesWFilter(order: String, dir: String): List<Routine> {
//        val filteredRoutines = remoteDataSource.getRoutines()
//        if( order.equals("date") ){
//            if( dir.equals("asc")){
//                routinesMutex.withLock {
//                    this.routines = filteredRoutines.content.sortedBy { it.date }.map { it.asModel() }
//                }
//            }else{
//                routinesMutex.withLock {
//                    this.routines = filteredRoutines.content.sortedByDescending { it.date }.map { it.asModel() }
//                }
//            }
//        }else if(order.equals(FilterOptions.Rating_Up.order)){
//
//        }
//        routinesMutex.withLock {
//            this.routines = filteredRoutines.content.sortedBy { it.date }.map { it.asModel() }
//        }
//        return routinesMutex.withLock { this.routines }
//    }


    suspend fun getRoutine(routineId: Int) : Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }
}