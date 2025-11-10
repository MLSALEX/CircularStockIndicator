package com.alexmls.minichallenges.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmls.minichallenges.R
import com.alexmls.minichallenges.home.domain.model.MiniChallengeDescriptor
import com.alexmls.minichallenges.home.domain.model.MiniChallengeId
import com.alexmls.minichallenges.home.domain.model.MiniChallengeMonth
import com.alexmls.minichallenges.home.presentation.components.MiniChallengeMonthSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    onNavigateToChallenge: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        months = state.months,
        onChallengeClick = onNavigateToChallenge
    )
}

@Composable
fun HomeScreen(
    months: List<MiniChallengeMonth>,
    onChallengeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    MiniChallengesBackground(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Text(
                text = "MINICHALLENGES",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp
                ),
                color = Color.White
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(months) { month ->
                    MiniChallengeMonthSection(
                        month = month,
                        onChallengeClick = onChallengeClick
                    )
                }
            }
        }
    }
}

@Composable
fun MiniChallengesBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                val linear = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.0f  to Color(0xFFFF8AB8),
                        0.20f to Color(0xFFF6B6FF),
                        0.35f to Color(0xFFC7A4FF),
                        0.70f to Color(0xFF7BD5FF),
                        0.85f to Color(0xFF70F0D5),
                        1.00f to Color(0xFFFFE6A3),
                    ),
                    start = Offset(-0.25f * size.width, -0.1f * size.height),
                    end   = Offset(1.1f * size.width, 1.1f * size.height)
                )

                drawRect(
                    brush = linear,
                    size = size
                )

                val radial = Brush.radialGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.30f),
                        Color.Transparent
                    ),
                    center = Offset(size.width * 0.6f, size.height * 0.3f),
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPr() {
    MaterialTheme {
        val sampleMonths = listOf(
            MiniChallengeMonth(
                monthIndex = 3,
                year = 2025,
                title = "March",
                challenges = listOf(
                    MiniChallengeDescriptor(
                        id = MiniChallengeId("counter"),
                        title = "Counter",
                        description = "Simple counter challenge",
                        monthIndex = 3,
                        year = 2025,
                        imageRes = R.drawable.circular_indicator
                    ),
                    MiniChallengeDescriptor(
                        id = MiniChallengeId("image_search"),
                        title = "Image Search",
                        description = "Search images UI",
                        monthIndex = 3,
                        year = 2025,
                        imageRes = R.drawable.circular_indicator
                    ),
                )
            ),
            MiniChallengeMonth(
                monthIndex = 2,
                year = 2025,
                title = "February",
                challenges = listOf(
                    MiniChallengeDescriptor(
                        id = MiniChallengeId("slider"),
                        title = "Circular indicator",
                        description = "Circular indicator UI",
                        monthIndex = 2,
                        year = 2025,
                        imageRes = R.drawable.circular_indicator
                    ),
                    MiniChallengeDescriptor(
                        id = MiniChallengeId("faq"),
                        title = "FAQ",
                        description = "Expandable FAQ cards",
                        monthIndex = 2,
                        year = 2025,
                        imageRes = R.drawable.circular_indicator
                    ),
                    MiniChallengeDescriptor(
                        id = MiniChallengeId("recipe"),
                        title = "Recipe",
                        description = "Recipe card layout",
                        monthIndex = 2,
                        year = 2025,
                        imageRes = R.drawable.circular_indicator
                    )
                )
            ),
        )

        HomeScreen(
            months = sampleMonths,
            onChallengeClick = { id ->
                println("Clicked challenge: $id")
            }
        )
    }
}
