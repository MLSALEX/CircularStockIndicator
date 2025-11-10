package com.alexmls.minichallenges.circular_indicator.domain

interface ProductRepository {
    suspend fun getProduct(): Product
    suspend fun getCurrentStock(): Int
    suspend fun decreaseStock(): Int
}