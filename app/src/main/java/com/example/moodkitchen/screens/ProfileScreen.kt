package com.example.moodkitchen.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary
import com.example.moodkitchen.R


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onContinueClicked: () -> Unit,
    navController: NavHostController,
    isExistingProfile: Boolean
) {
    val profileState by profileViewModel.profile.collectAsState()
    val context = LocalContext.current
    //val isExistingProfile = profileState != null
    val buttonText = if (isExistingProfile) "Save Profile" else "Create Profile"

    var nameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var usernameState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var bioState by remember { mutableStateOf("") }
    var allergiesState by remember { mutableStateOf("") }
    var favoritesState by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(profileState, isExistingProfile) {
        if (isExistingProfile) {
            profileState?.let { loaded ->
                nameState = loaded.name
                emailState = loaded.email
                usernameState = loaded.username
                passwordState = loaded.password
                bioState = loaded.bio
                allergiesState = loaded.allergies
                favoritesState = loaded.favorites
            }
        } else {
            nameState = ""
            emailState = ""
            usernameState = ""
            passwordState = ""
            bioState = ""
            allergiesState = ""
            favoritesState = ""
        }
    }


    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = PeachBackground,
        unfocusedContainerColor = PeachBackground,
        focusedTextColor = TealPrimary,
        unfocusedTextColor = TealPrimary,
        cursorColor = TealPrimary,
        focusedIndicatorColor = TealPrimary,
        unfocusedIndicatorColor = OrangeSecondary
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "profileView"
            )
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text("Create / Edit Profile", style = MaterialTheme.typography.headlineSmall)

            TextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("Name") },
                colors = textFieldColors,
                maxLines = 1,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = emailState,
                onValueChange = { emailState = it },
                label = { Text("Email") },
                colors = textFieldColors,
                maxLines = 1,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = usernameState,
                onValueChange = { usernameState = it },
                label = { Text("Username") },
                colors = textFieldColors,
                maxLines = 1,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = passwordState,
                onValueChange = { passwordState = it },
                label = { Text("Password") },
                colors = textFieldColors,
                maxLines = 1,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Password"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = bioState,
                onValueChange = { bioState = it },
                label = { Text("Bio") },
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = allergiesState,
                onValueChange = { allergiesState = it },
                label = { Text("Allergies") },
                colors = textFieldColors,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = favoritesState,
                onValueChange = { favoritesState = it },
                label = { Text("Favorites") },
                colors = textFieldColors,
                maxLines = 1,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onClick@
                {
                    val profileToSave = if (isExistingProfile) {
                        profileState!!.copy(
                            name = nameState,
                            email = emailState,
                            username = usernameState,
                            password = passwordState,
                            bio = bioState,
                            allergies = allergiesState,
                            favorites = favoritesState
                        )
                    } else {
                        Profile(
                            name = nameState,
                            email = emailState,
                            username = usernameState,
                            password = passwordState,
                            bio = bioState,
                            allergies = allergiesState,
                            favorites = favoritesState
                        )
                    }

                    profileViewModel.saveProfile(profileToSave)

                    Toast.makeText(context, "Profile info saved!", Toast.LENGTH_SHORT).show()
                    onContinueClicked()
                },

                colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            {
                Text(buttonText, fontSize = 18.sp, color = MaterialTheme.colorScheme.background)
            }


            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.outline_award_meal_24),
                    contentDescription = "App logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        }

        /* Logout Button
            if (loggedInState) {
                Button(
                    onClick = {
                        profileViewModel.logOut()
                        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
                        onGoHome()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Log Out", fontSize = 18.sp, color = MaterialTheme.colorScheme.background)
                }
            } */



    }
}


