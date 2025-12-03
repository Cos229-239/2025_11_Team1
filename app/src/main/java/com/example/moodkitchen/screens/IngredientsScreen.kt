package com.example.moodkitchen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary

@Composable
fun IngredientsScreen(
    navController: NavHostController,
    onGoHome: () -> Unit
) {
    var ingredientText by remember { mutableStateOf("") }
    var ingredientsList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PeachBackground)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "What's in your Pantry?",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 24.dp),
                    color = TealPrimary
        )

        // Input field
        OutlinedTextField(
            value = ingredientText,
            onValueChange = { ingredientText = it },
            label = { Text("Type your ingredients here...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // ADD button
        Button(
            onClick = {
                if (ingredientText.isNotBlank()) {
                    ingredientsList = ingredientsList + ingredientText
                    ingredientText = ""
                }
            },
            shape = RoundedCornerShape(size = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "ADD", fontSize = 18.sp)
        }

        Spacer(Modifier.height(24.dp))

        // List of ingredients
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(ingredientsList) { ingredient ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = ingredient, fontSize = 16.sp)
                    Button(
                        onClick = {
                            ingredientsList = ingredientsList.filter { it != ingredient }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Delete")
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // NEXT button
        Button(
            onClick = { val ingredientsString = ingredientsList.joinToString(",")
                navController.navigate(route = "moodSelection/$ingredientsString")
            },
            shape = RoundedCornerShape(size = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "NEXT", fontSize = 18.sp)
        }
    }
}
