package com.alexmls.minichallenges.circular_indicator.di


import ProductViewModel
import com.alexmls.minichallenges.circular_indicator.data.ProductRepositoryImpl
import com.alexmls.minichallenges.circular_indicator.domain.ProductRepository
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val circularIndicatorModule = module {
    factory<ProductRepository> { ProductRepositoryImpl() }
    viewModelOf (::ProductViewModel)
}