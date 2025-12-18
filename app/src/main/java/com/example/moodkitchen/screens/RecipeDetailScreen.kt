package com.example.moodkitchen.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.moodkitchen.model.Recipe
import com.example.moodkitchen.screens.BottomNavigationBar
import com.example.moodkitchen.ui.theme.TealPrimary
import viewmodel.RecipeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit,
    navController: NavHostController,
    onGoHome: () -> Unit,
    onProfileClicked: () -> Unit
) {
    val recipeViewModel: RecipeViewModel = viewModel()
    var recipe by remember { mutableStateOf<Recipe?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    // Fetch recipe details when screen opens
    LaunchedEffect(recipeId) {
        isLoading = true
        recipe = recipeViewModel.fetchRecipeById(recipeId)
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe?.name ?: "Loading...") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "recipeDetail"
            )
        }
    ) { innerPadding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            recipe?.let { r ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Image
                    r.imageUrl?.let { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = r.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .padding(bottom = 16.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Description
                    Text(text = r.description, style = MaterialTheme.typography.bodyMedium)

                    // Ingredients
                    if (r.ingredients.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Ingredients:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        r.ingredients.forEach { Text("• $it") }
                    }

                    // Directions
                    if (r.directions.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Directions:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(r.directions)
                    }
                }
            }
        }
    }
}
