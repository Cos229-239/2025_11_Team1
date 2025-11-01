package com.example.moodkitchen_jenn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        startDestination = "moodSelection"
    ) {
        composable("moodSelection") {
            MoodSelectionScreen { selectedMood ->
                navController.navigate("recipes/$selectedMood")
            }
        }
        composable("recipes/{mood}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: ""
            RecipeListScreen(mood)
        }
    }

}

