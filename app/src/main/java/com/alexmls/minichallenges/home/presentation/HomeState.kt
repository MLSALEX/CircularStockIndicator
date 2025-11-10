package com.alexmls.minichallenges.home.presentation

import com.alexmls.minichallenges.home.domain.model.MiniChallengeMonth

data class HomeState(
    val months: List<MiniChallengeMonth> = emptyList()
)