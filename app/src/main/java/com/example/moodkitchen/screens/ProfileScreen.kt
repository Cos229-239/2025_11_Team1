package com.example.moodkitchen.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodkitchen.data.Profile
import com.example.moodkitchen.data.ProfileViewModel
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import com.example.moodkitchen.ui.theme.OrangeSecondary


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    onGoHome: () -> Unit,
    onBackToMoods: () -> Unit,
    onContinueClicked: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    var favorites by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Create / Edit Profile",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        val textFieldColors = TextFieldDefaults.colors(
            focusedContainerColor = PeachBackground,
            unfocusedContainerColor = PeachBackground,
            focusedTextColor = TealPrimary,
            unfocusedTextColor = TealPrimary,
            cursorColor = TealPrimary,
            focusedIndicatorColor = TealPrimary,
            unfocusedIndicatorColor = OrangeSecondary
        )

        Text("Create / Edit Profile", style = MaterialTheme.typography.headlineSmall)
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            colors = textFieldColors,
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = textFieldColors,
        )

        TextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio") },
            colors = textFieldColors,
        )

        TextField(
            value = allergies,
            onValueChange = { allergies = it },
            label = { Text("Allergies") },
            colors = textFieldColors,
        )

        TextField(
            value = favorites,
            onValueChange = { favorites = it },
            label = { Text("Favorites") },
            colors = textFieldColors,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val newProfile = Profile(
                    name = name,
                    email = email,
                    bio = bio,
                    allergies = allergies,
                    favorites = favorites
                )
                profileViewModel.saveProfile(newProfile)
            },
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Save Profile", fontSize = 18.sp, color = MaterialTheme.colorScheme.background)
        }

        Button(
            onClick = onBackToMoods,
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("← Back to Moods", color = PeachBackground)
        }

        OutlinedButton(
            onClick = onGoHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🏠 Home", color = TealPrimary)
        }
    }
}
