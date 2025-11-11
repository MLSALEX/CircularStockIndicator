package com.alexmls.minichallenges.core.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import com.alexmls.minichallenges.ui.theme.HomeColors

@Composable
fun HomeBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                val linear = HomeColors.backgroundGradient(
                    sizeWidth = size.width,
                    sizeHeight = size.height
                )

                drawRect(
                    brush = linear,
                    size = size
                )

                val radial = HomeColors.radialHighlight(
                    sizeWidth = size.width,
                    sizeHeight = size.height,
                    radius = size.maxDimension * 0.7f
                )

                drawRect(
                    brush = radial,
                    size = size
                )
            }
    ) {
        content()
    }
}