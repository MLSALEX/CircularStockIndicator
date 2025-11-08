package com.alexmls.circularstocktracker.domain

data class Product(
    val name: String,
    val description: String,
    val discountedPrice: Double,
    val originalPrice: Double,
    val stock: Int,
    val imageUrl: String
)