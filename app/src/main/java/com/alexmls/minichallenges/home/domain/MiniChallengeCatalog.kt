package com.alexmls.minichallenges.home.domain

import com.alexmls.minichallenges.home.domain.model.MiniChallengeDescriptor
import com.alexmls.minichallenges.home.domain.model.MiniChallengeMonth

interface MiniChallengeCatalog {
    fun getAllChallenges(): List<MiniChallengeDescriptor>
}

class GetMiniChallengeCalendarUseCase(
    private val catalog: MiniChallengeCatalog
) {
    operator fun invoke(): List<MiniChallengeMonth> {
        val all = catalog.getAllChallenges()

        return all
            .groupBy { it.year to it.monthIndex }
            .entries
            .map { (yearMonth, challenges) ->
                val (year, monthIndex) = yearMonth
                MiniChallengeMonth(
                    monthIndex = monthIndex,
                    year = year,
                    title = formatMonthTitle(monthIndex, year),
                    challenges = challenges.sortedBy { it.title }
                )
            }
            .sortedWith(
                compareBy<MiniChallengeMonth> { it.year }
                    .thenBy { it.monthIndex }
            )
    }

    private fun formatMonthTitle(monthIndex: Int, year: Int): String {
        val monthName = when (monthIndex) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> "Unknown"
        }
        return "$monthName $year"
    }
}
