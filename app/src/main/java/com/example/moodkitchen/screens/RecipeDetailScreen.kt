package com.example.moodkitchen.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodkitchen.model.Recipe
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.TealPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(recipe: Recipe, onBack: () -> Unit, onGoHome: () -> Unit, onProfileClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedButton(onClick = onGoHome, modifier = Modifier.fillMaxWidth()) {
                    Text("🏠 Home", color = TealPrimary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = onProfileClicked, modifier = Modifier.fillMaxWidth()) {
                    Text("Profile", color = TealPrimary)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = recipe.description, style = MaterialTheme.typography.bodyMedium)

            if (recipe.ingredients.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Ingredients:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                recipe.ingredients.forEach { Text("• $it") }
            }

            if (recipe.directions.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Directions:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(recipe.directions)
            }

        }
    }
}