package com.alexmls.minichallenges.circular_indicator.domain

data class Product(
    val name: String,
    val description: String,
    val discountedPrice: Double,
    val originalPrice: Double,
    val stock: Int,
    val imageUrl: String
)