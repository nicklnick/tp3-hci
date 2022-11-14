package com.example.fitness_first.data.network

import com.example.fitness_first.data.network.api.ApiCategoryService
import com.example.fitness_first.data.network.api.ApiReviewService
import com.example.fitness_first.data.network.model.NetworkCategory
import com.example.fitness_first.data.network.model.NetworkPagedContent

class CategoryRemoteDataSource(
    private val apiCategoryService : ApiCategoryService
) : RemoteDataSource() {

    suspend fun getCategories(): NetworkPagedContent<NetworkCategory> {
        return handleApiResponse {
            apiCategoryService.getCategories()
        }
    }
    suspend fun getCategory(categoryId: Int): NetworkCategory {
        return handleApiResponse {
            apiCategoryService.getCategory(categoryId)
        }
    }

    suspend fun addCategory(category: NetworkCategory) : NetworkCategory {
        return handleApiResponse {
            apiCategoryService.addCategory(category)
        }
    }

}