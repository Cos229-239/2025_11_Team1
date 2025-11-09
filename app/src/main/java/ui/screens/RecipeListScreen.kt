package com.example.moodkitchen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodkitchen.data.RecipeRepository
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary
import androidx.compose.foundation.clickable
import com.example.moodkitchen.model.Recipe


@Composable
fun RecipeListScreen(mood: String, onGoHome: () -> Unit, onBackToMoods: () -> Unit, onRecipeClick: (Recipe) -> Unit) {
    val recipes = RecipeRepository.getRecipesForMood(mood)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PeachBackground)
            .padding(16.dp)
    ) {
        Text(
            text = "Recipes for when you're feeling $mood",
            style = MaterialTheme.typography.headlineSmall,
            color = TealPrimary
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onBackToMoods,
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("← Back to Moods", color = PeachBackground)
        }
        recipes.forEach { recipe ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onRecipeClick(recipe) },
                colors = CardDefaults.cardColors(containerColor = OrangeSecondary)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(recipe.name, style = MaterialTheme.typography.titleMedium, color = PeachBackground)
                    Text(recipe.description, style = MaterialTheme.typography.bodySmall, color = PeachBackground)
                }
            }
        }
        Spacer(Modifier.height(24.dp))

        OutlinedButton(
            onClick = onGoHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🏠 Home", color = TealPrimary)
        }
    }
}


