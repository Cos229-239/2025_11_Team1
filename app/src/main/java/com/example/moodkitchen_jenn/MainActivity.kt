package com.example.moodkitchen_jenn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodkitchen_jenn.ui.OnboardingScreen
import com.example.moodkitchen_jenn.ui.screens.MoodSelectionScreen
import com.example.moodkitchen_jenn.ui.screens.RecipeListScreen
import com.example.moodkitchen_jenn.ui.theme.MoodKitchenTheme

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
                onBackToMoods = { navController.navigate("moodSelection") }
            )
        }
    }
}
