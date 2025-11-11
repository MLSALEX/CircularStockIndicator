package com.alexmls.minichallenges.circular_indicator.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


// Backgrounds
val CI_BG = Color(0xFFF6F2ED)
val CI_Surface = Color(0xFFFFFFFF)
val CI_Outline = Color(0xFFDFDDDB)

// Text colors
val CI_TextPrimary = Color(0xFF211304)
val CI_TextSecondary = Color(0xFF6B5D4F)
val CI_TextDisabled = Color(0xFF9A9795)
val CI_TextAlt = Color(0xFFFFFFFF)

// Accent colors
val CI_Discount = Color(0xFF7C1414)
val CI_DiscountLight = Color(0xFFD33F3F)

object CiColors {
    val DiscountGradient = Brush.linearGradient(
        colorStops = arrayOf(
            0.0f to CI_DiscountLight,
            0.55f to CI_Discount,
            1.0f to CI_Discount
        )
    )
}