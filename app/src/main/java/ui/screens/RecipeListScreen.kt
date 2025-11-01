package com.example.moodkitchen_jenn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodkitchen_jenn.data.RecipeRepository
import com.example.moodkitchen_jenn.ui.theme.OrangeSecondary
import com.example.moodkitchen_jenn.ui.theme.PeachBackground
import com.example.moodkitchen_jenn.ui.theme.TealPrimary

@Composable
fun RecipeListScreen(mood: String) {
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

        recipes.forEach { recipe ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = OrangeSecondary)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(recipe.name, style = MaterialTheme.typography.titleMedium, color = PeachBackground)
                    Text(recipe.description, style = MaterialTheme.typography.bodySmall, color = PeachBackground)
                }
            }
        }
    }
}
