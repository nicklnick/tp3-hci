package com.example.fitness_first.data.repository

import com.example.fitness_first.data.model.Category
import com.example.fitness_first.data.network.CategoryRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CategoryRepository(
    private val remoteDataSource: CategoryRemoteDataSource
) {
    private val categoryMutex = Mutex()
    private var categories: List<Category> = emptyList()

    suspend fun getCategories(refresh: Boolean = false): List<Category> {
        if (refresh || categories.isEmpty()) {
            val result = remoteDataSource.getCategories()
            // Thread-safe write to latestNews
            categoryMutex.withLock {
                this.categories = result.content.map { it.asModel() }
            }
        }
        return categoryMutex.withLock { this.categories }
    }
    suspend fun getCategory(categoryId: Int) : Category {
        return remoteDataSource.getCategory(categoryId).asModel()
    }

    suspend fun addCategory(category: Category) : Category {
        val newCategory = remoteDataSource.addCategory(category.asNetworkModel()).asModel()
        categoryMutex.withLock {
            this.categories = emptyList()
        }
        return newCategory
    }
}