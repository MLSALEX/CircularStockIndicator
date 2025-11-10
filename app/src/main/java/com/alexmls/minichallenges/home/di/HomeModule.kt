package com.alexmls.minichallenges.home.di

import com.alexmls.minichallenges.home.data.StaticMiniChallengeCatalog
import com.alexmls.minichallenges.home.domain.GetMiniChallengeCalendarUseCase
import com.alexmls.minichallenges.home.domain.MiniChallengeCatalog
import com.alexmls.minichallenges.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    single<MiniChallengeCatalog> { StaticMiniChallengeCatalog() }
    factory { GetMiniChallengeCalendarUseCase(catalog = get()) }

    viewModelOf (::HomeViewModel)
}