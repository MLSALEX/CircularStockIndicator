package com.alexmls.minichallenges.circular_indicator.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = CI_BG,
    surface = CI_Surface,
    outline = CI_Outline,
    primary = CI_Discount,
    onPrimary = CI_TextAlt,
    secondary = CI_TextSecondary,
    onSecondary = CI_TextAlt,
    secondaryContainer = CI_TextDisabled,
    error = CI_Discount,
    onError = CI_TextAlt,
    onBackground = CI_TextPrimary,
    onSurface = CI_TextPrimary,
)

@Composable
fun CircularIndicatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}