package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.Routine
import com.example.fitness_first.data.network.FavouriteRemoteDataSource
import com.example.fitness_first.data.network.RemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class FavouriteRepository (
    private val remoteDataSource: FavouriteRemoteDataSource
){
    private val favouriteMutex = Mutex()
    private var favouriteRoutines: List<Routine> = emptyList()

    suspend fun getFavourites(): List<Routine>{
        val result = remoteDataSource.getFavourites()
        favouriteMutex.withLock {
            this.favouriteRoutines = result.content.map { it.asModel() }
        }
        return favouriteMutex.withLock { this.favouriteRoutines }
    }

    suspend fun markFavourite(routineId: Int){
        remoteDataSource.markFacourite(routineId)
        favouriteMutex.withLock {
            this.favouriteRoutines = emptyList()
        }
    }

    suspend fun removeFavourite(routineId: Int){
        remoteDataSource.deleteFavourite(routineId)
        favouriteMutex.withLock {
            this.favouriteRoutines = emptyList()
        }
    }
}