package com.example.moodkitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodkitchen.screens.OnboardingScreen
import com.example.moodkitchen.screens.RecipeListScreen
import com.example.moodkitchen.ui.theme.MoodKitchenTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.moodkitchen.screens.RecipeDetailScreen
import com.example.moodkitchen.data.RecipeRepository
import com.example.moodkitchen.screens.ProfileDetailScreen
import com.example.moodkitchen.screens.ProfileScreen
import com.example.moodkitchen.screens.MoodSelectionScreen

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
        startDestination = "OnboardingScreen" //start with onboarding
    ) {
        composable("OnboardingScreen") {
            OnboardingScreen(
                onContinueClicked = { navController.navigate("moodSelection") },
                onProfileClicked = { navController.navigate("profileScreen") }
            )
        }

        composable("moodSelection") {
            MoodSelectionScreen(
                onMoodSelected = { selectedMood ->
                    navController.navigate("recipes/$selectedMood")
                },
                onGoHome = { navController.navigate("OnboardingScreen") } ,
                onProfileClicked = { navController.navigate("profileDetail") }
            )
        }

        composable("profileScreen") {
            ProfileScreen(
                onContinueClicked = { navController.navigate("profileDetail") },
                onGoHome = { navController.navigate("OnboardingScreen") },
                onBackToMoods = { navController.navigate("moodSelection") }
            )
        }

        composable("profileDetail") {
            ProfileDetailScreen(

                    onContinueClicked = { navController.navigate("moodSelection") },
                    onBack = { navController.popBackStack() }
            )
        }

        composable("recipes/{mood}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: ""
            RecipeListScreen(
                mood = mood,
                onGoHome = { navController.navigate("OnboardingScreen") },
                onBackToMoods = { navController.navigate("moodSelection") },
                onRecipeClick = { recipe ->
                    navController.navigate("recipeDetail/${mood}/${recipe.name}")
                },
                onProfileClicked = { navController.navigate("profileScreen") }
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
                onGoHome = { navController.navigate("OnboardingScreen") },
                onProfileClicked = { navController.navigate("profileScreen") }
            )
        }
    }
}
