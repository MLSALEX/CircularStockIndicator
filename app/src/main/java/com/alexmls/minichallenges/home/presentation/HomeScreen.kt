package com.alexmls.minichallenges.home.presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmls.minichallenges.R
import com.alexmls.minichallenges.core.designsystem.HomeBackground
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
    HomeBackground(
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
