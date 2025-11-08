package com.alexmls.circularstocktracker.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


// Backgrounds
val BG = Color(0xFFF6F2ED)
val Surface = Color(0xFFFFFFFF)
val Outline = Color(0xFFDFDDDB)

// Text colors
val TextPrimary = Color(0xFF211304)
val TextSecondary = Color(0xFF6B5D4F)
val TextDisabled = Color(0xFF9A9795)
val TextAlt = Color(0xFFFFFFFF)

// Accent colors
val Discount = Color(0xFF7C1414)
val DiscountLight = Color(0xFFD33F3F)

object AppColors {
    val DiscountGradient = Brush.linearGradient(
        colorStops = arrayOf(
            0.0f to DiscountLight,
            0.55f to Discount,
            1.0f to Discount
        )
    )


}