package com.alexmls.minichallenges.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val HomeColorScheme = lightColorScheme(
    background = HomeCardBackgroundBottom,
    surface = HomeCardBackgroundTop,
    primary = HomeBackgroundViolet,
    onPrimary = HomeHeaderTitleColor,
    secondary = HomeMonthTitleColor,
    onSecondary = HomeHeaderTitleColor,
    outline = HomeCardBorderColor,
    onBackground = HomeCardTitleColor,
    onSurface = HomeCardTitleColor,
)

@Composable
fun MiniChallengesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = HomeColorScheme,
        typography = Typography,
        content = content
    )
}