package com.alexmls.minichallenges.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Background gradient colors
val HomeBackgroundPink = Color(0xFFFF8AB8)
val HomeBackgroundLilac = Color(0xFFF6B6FF)
val HomeBackgroundViolet = Color(0xFFC7A4FF)
val HomeBackgroundSky = Color(0xFF7BD5FF)
val HomeBackgroundMint = Color(0xFF70F0D5)
val HomeBackgroundWarm = Color(0xFFFFE6A3)

val HomeBackgroundRadialHighlight = Color.White.copy(alpha = 0.30f)

// Card surface gradient
val HomeCardBackgroundTop = Color(0xFFFFFFFF)
val HomeCardBackgroundBottom = Color(0xFFE7EAF4)

// Card borders / overlays
val HomeCardBorderColor = Color.White.copy(alpha = 0.9f)


// Text colors
val HomeHeaderTitleColor = Color.White
val HomeMonthTitleColor = Color(0xFF3E4B67)
val HomeCardTitleColor = Color(0xFF202124)

object HomeColors {

    fun backgroundGradient(
        sizeWidth: Float,
        sizeHeight: Float
    ): Brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.0f  to HomeBackgroundPink,
            0.20f to HomeBackgroundLilac,
            0.35f to HomeBackgroundViolet,
            0.70f to HomeBackgroundSky,
            0.85f to HomeBackgroundMint,
            1.00f to HomeBackgroundWarm,
        ),
        start = Offset(-0.25f * sizeWidth, -0.1f * sizeHeight),
        end   = Offset(1.1f * sizeWidth, 1.1f * sizeHeight)
    )

    fun radialHighlight(sizeWidth: Float, sizeHeight: Float, radius: Float): Brush =
        Brush.radialGradient(
            colors = listOf(
                HomeBackgroundRadialHighlight,
                Color.Transparent
            ),
            center = Offset(
                sizeWidth * 0.6f,
                sizeHeight * 0.3f
            ),
            radius = radius
        )
}