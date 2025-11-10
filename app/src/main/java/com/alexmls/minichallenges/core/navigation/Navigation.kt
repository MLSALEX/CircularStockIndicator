package com.alexmls.minichallenges.core.navigation
import ProductRoot
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alexmls.minichallenges.home.presentation.HomeRoot
import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object Home : Destination

    @Serializable
    data object CircularIndicator : Destination

}

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home,
        modifier = modifier
            .then(Modifier)
    ) {
        composable<Destination.Home> {
            HomeRoot(
                onNavigateToChallenge = { challengeId ->
                    MiniChallengeNavigationMapper
                        .toDestination(challengeId)
                        ?.let { destination ->
                            navController.navigate(destination)
                        }
                }
            )
        }

        composable<Destination.CircularIndicator> {
            ProductRoot(

            )
        }
    }
}

object MiniChallengeNavigationMapper {
    fun toDestination(id: String): Destination? = when (id) {
        "circular_indicator" -> Destination.CircularIndicator
        else -> null
    }
}