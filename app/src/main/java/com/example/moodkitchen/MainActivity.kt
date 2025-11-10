package com.example.moodkitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodkitchen.screens.OnboardingScreen
import com.example.moodkitchen.ui.screens.MoodSelectionScreen
import com.example.moodkitchen.ui.screens.RecipeListScreen
import com.example.moodkitchen.ui.theme.MoodKitchenTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.moodkitchen.ui.screens.RecipeDetailScreen
import com.example.moodkitchen.data.RecipeRepository


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodKitchenTheme {
                MoodKitchenApp()
            }
        }
    }
}

@Composable
fun MoodKitchenApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding" //start with onboarding
    ) {
        composable("onboarding") {
            OnboardingScreen(onContinueClicked = {
                navController.navigate("moodSelection")
            })
        }

        composable("moodSelection") {
            MoodSelectionScreen(
                onMoodSelected = { selectedMood ->
                    navController.navigate("recipes/$selectedMood")
                },
                onGoHome = { navController.navigate("onboarding") }
            )
        }

        composable("recipes/{mood}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: ""
            RecipeListScreen(
                mood = mood,
                onGoHome = { navController.navigate("onboarding") },
                onBackToMoods = { navController.navigate("moodSelection") },
                onRecipeClick = { Recipe ->
                    navController.navigate("recipeDetail/${mood}/${Recipe.name}")
                }
            )
        }
        composable(
            "recipeDetail/{mood}/{recipeName}",
            arguments = listOf(
                navArgument("mood") { type = NavType.StringType },
                navArgument("recipeName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: ""
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            val recipe = RecipeRepository.getRecipesForMood(mood)
                .firstOrNull { it.name == recipeName } ?: return@composable

            RecipeDetailScreen(
                recipe = recipe,
                onBack = { navController.popBackStack() },
                onGoHome = { navController.navigate("onboarding") }
            )
        }
    }
}
