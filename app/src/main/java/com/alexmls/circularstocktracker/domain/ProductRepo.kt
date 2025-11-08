package com.alexmls.circularstocktracker.domain

interface ProductRepository {
    suspend fun getProduct(): Product
    suspend fun getCurrentStock(): Int
    suspend fun decreaseStock(): Int
}