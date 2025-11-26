package com.example.moodkitchen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodkitchen.screens.OnboardingScreen
import com.example.moodkitchen.ui.screens.MoodSelectionScreen
import com.example.moodkitchen.ui.screens.RecipeListScreen
import com.example.moodkitchen.ui.theme.MoodKitchenTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument
import viewmodel.ProfileViewModel
import com.example.moodkitchen.ui.screens.RecipeDetailScreen
import com.example.moodkitchen.data.RecipeRepository
import com.example.moodkitchen.screens.LoginDialog
import com.example.moodkitchen.screens.ProfileScreen


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
    val profileViewModel: ProfileViewModel = viewModel()
    val profile by profileViewModel.profile.collectAsState()
    val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()

    // Show login dialog only if user exists and not logged in
    var showLoginDialog by remember { mutableStateOf(false) }

    LaunchedEffect(profile, isLoggedIn) {
        showLoginDialog = profile != null && profile!!.username.isNotEmpty() && !isLoggedIn
    }

    if (showLoginDialog && profile != null) {
        LoginDialog(
            profile = profile!!,
            onLoginSuccess = {
                profileViewModel.logIn()
                showLoginDialog = false
            },
            onCancel = { showLoginDialog = false }
        )
    }

    NavHost(
        navController = navController,
        startDestination = "OnboardingScreen"
    ) {
        composable("OnboardingScreen") {
            OnboardingScreen(
                navController = navController,
                onContinueClicked = { navController.navigate("moodSelection") },
                onProfileClicked = { navController.navigate("profileScreen") },
                profileViewModel = profileViewModel
            )
        }

        composable("profileScreen") {
            val profile by profileViewModel.profile.collectAsState()
            val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()

            ProfileScreen(
                profileViewModel = profileViewModel,
                profile = profile,
                isLoggedIn = isLoggedIn,
                onContinueClicked = { navController.navigate("moodSelection") },
                onGoHome = { navController.navigate("OnboardingScreen") },
                onBackToMoods = { navController.navigate("moodSelection") }
            )
        }


        composable("moodSelection") {
            MoodSelectionScreen(
                onMoodSelected = { selectedMood ->
                    navController.navigate("recipes/$selectedMood")
                },
                onGoHome = { navController.navigate("OnboardingScreen") },
                onProfileClicked = { navController.navigate("profileScreen") }
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
