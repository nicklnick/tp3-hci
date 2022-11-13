package com.example.fitness_first.data.network

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)