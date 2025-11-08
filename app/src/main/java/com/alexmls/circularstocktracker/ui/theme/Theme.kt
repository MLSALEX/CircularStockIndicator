package com.alexmls.circularstocktracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = BG,
    surface = Surface,
    outline = Outline,
    primary = Discount,
    onPrimary = TextAlt,
    secondary = TextSecondary,
    onSecondary = TextAlt,
    secondaryContainer = TextDisabled,
    error = Discount,
    onError = TextAlt,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
)

@Composable
fun CircularStockTrackerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}