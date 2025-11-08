package com.alexmls.circularstocktracker.di


import ProductViewModel
import com.alexmls.circularstocktracker.data.ProductRepositoryImpl
import com.alexmls.circularstocktracker.domain.ProductRepository
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productModule = module {
    single<ProductRepository> { ProductRepositoryImpl() }
    viewModelOf (::ProductViewModel)
}