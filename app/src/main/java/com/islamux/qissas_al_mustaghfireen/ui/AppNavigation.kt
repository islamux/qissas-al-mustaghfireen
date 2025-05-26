package com.islamux.qissas_al_mustaghfireen.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.islamux.qissas_al_mustaghfireen.viewmodel.StoryViewModel

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object StoryDetailScreen : Screen("story_detail_screen/{storyId}") {
        fun createRoute(storyId: Int) = "story_detail_screen/$storyId"
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    storyViewModel: StoryViewModel = viewModel() // Use viewModel delegate here
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                storyViewModel = storyViewModel,
                onNavigateToStory = { storyId ->
                    // ViewModel's selectStory is now called from StoryDetailScreen
                    // after retrieving the ID, or could be called here if needed before navigation.
                    // For now, the selection logic is primarily in the ViewModel,
                    // triggered by StoryDetailScreen observing selectedStory after an ID is passed.
                    navController.navigate(Screen.StoryDetailScreen.createRoute(storyId))
                }
            )
        }
        composable(
            route = Screen.StoryDetailScreen.route,
            arguments = listOf(navArgument("storyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val storyId = backStackEntry.arguments?.getInt("storyId")
            if (storyId != null) {
                // Call selectStory here when the StoryDetailScreen is navigated to.
                // This ensures the ViewModel updates the selectedStory StateFlow.
                storyViewModel.selectStory(storyId)
                StoryDetailScreen(
                    storyViewModel = storyViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            } else {
                // Handle error: storyId not found, perhaps navigate back or show error
                navController.popBackStack()
            }
        }
    }
}
