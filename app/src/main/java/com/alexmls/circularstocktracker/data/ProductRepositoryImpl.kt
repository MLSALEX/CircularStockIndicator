package com.alexmls.circularstocktracker.data

import com.alexmls.circularstocktracker.domain.Product
import com.alexmls.circularstocktracker.domain.ProductRepository
import kotlinx.coroutines.delay

class ProductRepositoryImpl : ProductRepository {
    private val product = Product(
        name = "Nike Air Zoom Pegasus 41",
        description = "Legendary running shoes with Air Zoom technology and ReactX cushioning for daily training and marathons.",
        discountedPrice = 100.0,
        originalPrice = 160.0,
        stock = 12,
        imageUrl = ""
    )
    private var stockLeft: Int = product.stock
    override suspend fun getProduct(): Product {
        delay(200)
        return product
    }

    override suspend fun decreaseStock(): Int {
        if (stockLeft > 0) {
            delay(300)
            stockLeft -= 1
        }
        return stockLeft
    }

    override suspend fun getCurrentStock(): Int {
        return stockLeft
    }
}