package com.example.moodkitchen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.moodkitchen.R
import com.example.moodkitchen.ui.theme.OrangeSecondary
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onGoHome: () -> Unit,
    onBackToMoods: () -> Unit,
    onContinueClicked: () -> Unit,
) {
    val profile by profileViewModel.profile.collectAsState()
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    var favorites by remember { mutableStateOf("") }

LaunchedEffect(profile) {

    profile?.let { loaded->
        password = loaded.password
        name = loaded.name
        email = loaded.email
        bio = loaded.bio
        allergies = loaded.allergies
        favorites = loaded.favorites
    }
}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

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
        Spacer(modifier = Modifier.height(10.dp))


        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = textFieldColors,
        )
        Spacer(modifier = Modifier.height(10.dp))

        var passwordVisible by remember { mutableStateOf(false) }
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )



        TextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio") },
            colors = textFieldColors,
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = allergies,
            onValueChange = { allergies = it },
            label = { Text("Allergies") },
            colors = textFieldColors,
        )
        Spacer(modifier = Modifier.height(10.dp))

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
                    password = password,
                    bio = bio,
                    allergies = allergies,
                    favorites = favorites
                )
                profileViewModel.saveProfile(newProfile)
            },
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text("Save Profile", fontSize = 18.sp, color = MaterialTheme.colorScheme.background)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackToMoods,
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Moods", color = PeachBackground)
        }

        OutlinedButton(
            onClick = onGoHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🏠 Home", color = TealPrimary)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), // optional top padding
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.outline_award_meal_24),
                contentDescription = "App logo",
                modifier = Modifier.size(150.dp) // adjust size as needed
            )
        }
    }
}
