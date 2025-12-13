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
import androidx.compose.ui.platform.LocalContext
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
import com.example.moodkitchen.data.PantryManager
import viewmodel.ProfileViewModel
import com.example.moodkitchen.ui.screens.RecipeDetailScreen
import com.example.moodkitchen.data.RecipeRepository
import com.example.moodkitchen.model.Recipe
import com.example.moodkitchen.screens.LoginDialog
import com.example.moodkitchen.screens.ProfileScreen
import com.example.moodkitchen.screens.IngredientsScreen


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
                navController = navController,
                profileViewModel = profileViewModel,
                profile = profile,
                isLoggedIn = isLoggedIn,
                onContinueClicked = { navController.navigate(route = "moodSelection/") },
                onGoHome = { navController.navigate("OnboardingScreen") },
                onBackToMoods = { navController.navigate("moodSelection/") }
            )
        }

        composable(route = "ingredientsScreen") {
            IngredientsScreen(
                navController = navController,
                onGoHome = { navController.navigate(route = "OnboardingScreen") }
            )
        }


        composable(route = "moodSelection/{ingredients}",
            arguments = listOf(
                navArgument("ingredients") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val ingredientsString = backStackEntry.arguments?.getString("ingredients") ?: ""
            val ingredientsList = if (ingredientsString.isNotEmpty()) {
                ingredientsString.split(",")
            } else {
                emptyList()
            }

            MoodSelectionScreen(
                navController = navController,
                ingredients = ingredientsList,   // pass the pantry
                onMoodSelected = { selectedMood ->
                    navController.navigate("recipes/$selectedMood/${ingredientsList.joinToString(",")}")
                },
                onGoHome = {
                    if (isLoggedIn) {
                        navController.navigate("ingredientsScreen") {
                            popUpTo("OnboardingScreen") { inclusive = true }
                        }
                    } else {
                        navController.navigate("OnboardingScreen")
                    }
                },
                onProfileClicked = { navController.navigate("profileScreen") }
            )
        }

        composable(route = "recipes/{mood}/{ingredients}") { backStackEntry ->
            val mood = backStackEntry.arguments?.getString("mood") ?: ""
                val pantryManager = PantryManager(LocalContext.current)
                val ingredientsList = if (!backStackEntry.arguments?.getString("ingredients").isNullOrEmpty()) {
                    backStackEntry.arguments!!.getString("ingredients")!!.split(",")
                } else {
                    pantryManager.getIngredients()
                }


                RecipeListScreen(
                navController = navController,
                mood = mood,
                onGoHome = { navController.navigate(route = "OnboardingScreen") },
                onBackToMoods = { navController.navigate(route = "moodSelection/$ingredientsList") },
                onRecipeClick = { recipe: Recipe ->
                    navController.navigate(route = "recipeDetail/${mood}/${recipe.name}")
                },
                onProfileClicked = { navController.navigate(route = "profileScreen") },
                userIngredients = ingredientsList  // Pass the ingredients!
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
