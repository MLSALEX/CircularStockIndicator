package com.alexmls.minichallenges.home.data

import com.alexmls.minichallenges.R
import com.alexmls.minichallenges.home.domain.MiniChallengeCatalog
import com.alexmls.minichallenges.home.domain.model.MiniChallengeDescriptor
import com.alexmls.minichallenges.home.domain.model.MiniChallengeId

class StaticMiniChallengeCatalog : MiniChallengeCatalog {

    override fun getAllChallenges(): List<MiniChallengeDescriptor> {
        val year = 2025

        val novemberChallenges = listOf(
            MiniChallengeDescriptor(
                id = MiniChallengeId("circular_indicator"),
                title = "Circular Stock Indicator",
                description = "Animate stock indicator with bounce effect when value changes.",
                monthIndex = 11,
                year = year,
                imageRes = R.drawable.circular_indicator,
            ),

        )

        return novemberChallenges
    }
}