package com.example.moodkitchen.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.moodkitchen.ui.theme.TealPrimary


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String
) {
    NavigationBar(
        containerColor = TealPrimary
    ) {
        NavigationBarItem(
            icon = { Text("🏠") },
            label = { Text("Home") },
            selected = currentRoute == "OnboardingScreen",
            onClick = { navController.navigate("OnboardingScreen") }
        )

        NavigationBarItem(
            icon = { Text("🥘") },
            label = { Text("Pantry") },
            selected = currentRoute == "ingredientsScreen",
            onClick = { navController.navigate("ingredientsScreen") }
        )

        NavigationBarItem(
            icon = { Text("😊") },
            label = { Text("Moods") },
            selected = currentRoute.contains("moodSelection"),
            onClick = { navController.navigate("moodSelection/") }
        )

        NavigationBarItem(
            icon = { Text("👤") },
            label = { Text("Profile") },
            selected = currentRoute == "profileView",
            onClick = { navController.navigate("profileView") }
        )
    }
}
