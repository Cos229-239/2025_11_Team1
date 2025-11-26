package com.example.moodkitchen.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodkitchen.data.Profile
import viewmodel.ProfileViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary
import com.example.moodkitchen.R


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onGoHome: () -> Unit,
    onBackToMoods: () -> Unit,
    onContinueClicked: () -> Unit,
    profile: Profile?,
    isLoggedIn: Boolean,
) {
    val profile by profileViewModel.profile.collectAsState()
    val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var usernameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var bioState by remember { mutableStateOf("") }
    var allergiesState by remember { mutableStateOf("") }
    var favoritesState by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Load profile from DB into text fields
    LaunchedEffect(profile) {
        profile?.let { loaded ->
            nameState = loaded.name
            emailState = loaded.email
            usernameState = loaded.username
            passwordState = loaded.password
            bioState = loaded.bio
            allergiesState = loaded.allergies
            favoritesState = loaded.favorites
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
            value = nameState,
            onValueChange = { nameState = it },
            label = { Text("Name") },
            colors = textFieldColors
        )

        TextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("Email") },
            colors = textFieldColors
        )

        TextField(
            value = usernameState,
            onValueChange = { usernameState = it },
            label = { Text("Username") },
            colors = textFieldColors
        )

        TextField(
            value = passwordState,
            onValueChange = { passwordState = it },
            label = { Text("Password") },
            colors = textFieldColors,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            }
        )

        TextField(
            value = bioState,
            onValueChange = { bioState = it },
            label = { Text("Bio") },
            colors = textFieldColors
        )

        TextField(
            value = allergiesState,
            onValueChange = { allergiesState = it },
            label = { Text("Allergies") },
            colors = textFieldColors
        )

        TextField(
            value = favoritesState,
            onValueChange = { favoritesState = it },
            label = { Text("Favorites") },
            colors = textFieldColors
        )

        //  SAVE BUTTON
        Button(
            onClick = {
                val newProfile = Profile(
                    name = nameState,
                    email = emailState,
                    username = usernameState,
                    password = passwordState,
                    bio = bioState,
                    allergies = allergiesState,
                    favorites = favoritesState
                )
                profileViewModel.saveProfile(newProfile)
                Toast.makeText(context, "Profile info saved!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text("Save Profile", fontSize = 18.sp, color = MaterialTheme.colorScheme.background)
        }

        // MOODS BUTTON
        Button(
            onClick = onBackToMoods,
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Moods", color = PeachBackground)
        }

        //  HOME BUTTON
        OutlinedButton(
            onClick = onGoHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🏠 Home", color = TealPrimary)
        }

        // LOGOUT BUTTON (only show if logged in)
        if (isLoggedIn) {
            Button(
                onClick = {
                    profileViewModel.logOut() // clears logged-in state but keeps profile saved
                    Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
                    onGoHome()
                },
                colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Log Out", color = MaterialTheme.colorScheme.background)
            }
        }

        // LOGO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.outline_award_meal_24),
                contentDescription = "App logo",
                modifier = Modifier.size(150.dp)
            )
        }
    }
}
