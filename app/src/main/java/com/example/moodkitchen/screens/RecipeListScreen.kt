package com.example.moodkitchen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moodkitchen.model.Recipe
import com.example.moodkitchen.screens.BottomNavigationBar
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary
import com.example.moodkitchen.viewmodel.RecipeViewModel

@Composable
fun RecipeListScreen(
    navController: NavHostController,
    mood: String,
    userIngredients: List<String> = emptyList(),
    onGoHome: () -> Unit,
    onBackToMoods: () -> Unit,
    onRecipeClick: Function<Unit>,
    onProfileClicked: () -> Unit
) {
    val recipeViewModel: RecipeViewModel = viewModel()
    val recipes by recipeViewModel.recipes.collectAsState()
    val isLoading by recipeViewModel.isLoading.collectAsState()
    val error by recipeViewModel.error.collectAsState()

    // Fetch recipes when screen loads
    LaunchedEffect(userIngredients) {
        if (userIngredients.isNotEmpty()) {
            recipeViewModel.fetchRecipes(userIngredients)
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "recipes"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PeachBackground)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recipes for when you're feeling $mood",
                style = MaterialTheme.typography.headlineSmall,
                color = TealPrimary
            )

            Spacer(Modifier.height(16.dp))

            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(32.dp))
                }
                error != null -> {
                    Text(
                        text = error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                recipes.isEmpty() -> {
                    Text(
                        text = "No recipes found with your ingredients. Try adding more items to your pantry!",
                        modifier = Modifier.padding(32.dp)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(recipes) { recipe ->
                            RecipeCard(recipe = recipe, onClick = {
                                navController.navigate("recipeDetail/${mood}/${recipe.name}")
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = OrangeSecondary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (!recipe.imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = recipe.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                color = PeachBackground
            )

            Text(
                text = recipe.description.take(100) + "...",
                style = MaterialTheme.typography.bodySmall,
                color = PeachBackground
            )
        }
    }
}
