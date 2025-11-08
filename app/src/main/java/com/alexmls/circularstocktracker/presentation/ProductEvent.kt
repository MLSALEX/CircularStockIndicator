package com.alexmls.circularstocktracker.presentation

sealed interface ProductEvent {
    data class ShowToast(val message: String) : ProductEvent
}