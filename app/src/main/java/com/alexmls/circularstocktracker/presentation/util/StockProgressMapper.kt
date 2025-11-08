package com.alexmls.circularstocktracker.presentation.util

private const val MAX_PROGRESS_FRACTION = 0.25f

fun mapStockToProgress(
    stock: Int,
    maxStock: Int,
    maxProgressFraction: Float = MAX_PROGRESS_FRACTION
): Float {
    if (maxStock <= 0) return 0f

    val clamped = stock.coerceIn(0, maxStock)
    val normalized = clamped.toFloat() / maxStock.toFloat()
    return normalized * maxProgressFraction
}