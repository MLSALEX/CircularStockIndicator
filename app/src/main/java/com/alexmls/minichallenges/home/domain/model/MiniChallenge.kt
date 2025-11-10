package com.alexmls.minichallenges.home.domain.model

import androidx.annotation.DrawableRes

@JvmInline
value class MiniChallengeId(val value: String)


data class MiniChallengeDescriptor(
    val id: MiniChallengeId,
    val title: String,
    val description: String,
    val monthIndex: Int,
    val year: Int,
    @DrawableRes val imageRes: Int,
)


data class MiniChallengeMonth(
    val monthIndex: Int,
    val year: Int,
    val title: String,
    val challenges: List<MiniChallengeDescriptor>
)