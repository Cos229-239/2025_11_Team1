package com.example.moodkitchen.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.ProfileViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.TealPrimary
import com.example.moodkitchen.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun ProfileViewScreen(
    profileViewModel: ProfileViewModel,
    onBackToProfile: () -> Unit,
    onGoHome: () -> Unit,
    navController: NavHostController,
) {
    val profile by profileViewModel.profile.collectAsState()
    val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()
    val context = LocalContext.current
    var showLoginDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "profileView"
            )
        }
    ) { innerPadding ->

        //Content Tree
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Tells user profile status
            when {
                !isLoggedIn -> {
                    Spacer(Modifier.height(24.dp))

                    if (profile == null) {

                        Text("You haven't created a profile yet.")
                        Spacer(Modifier.height(12.dp))
                        Button(
                            onClick = onBackToProfile)
                        {
                            Text("Create Profile")
                        }

                    }
                    else {

                        Text("You're not logged in yet.")
                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = { showLoginDialog = true },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                        {
                            Text("Log In", fontSize = 18.sp)
                        }

                        if (showLoginDialog) {
                            LoginDialog(
                                profile = profile!!,
                                onLoginSuccess = {
                                    profileViewModel.logIn()
                                    showLoginDialog = false
                                    navController.navigate("ingredientsScreen")
                                },
                                onCancel = { showLoginDialog = false }
                            )
                        }
                    }
                }

                //Profile Information
                else -> {
                    val p = profile!!

                    val textPadding = 10.dp
                    val textColor = Color(0x48CCB3AC)

                    Text(p.name, fontSize = 42.sp)
                    Text(
                        p.username,
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 30.dp)
                    )

                    Text(
                        text = p.bio,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(textColor, RoundedCornerShape(8.dp))
                            .padding(textPadding),
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Allergies: ${p.allergies}", fontSize = 20.sp)
                    Text("Favorites: ${p.favorites}", fontSize = 20.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { navController.navigate("profileScreen/existing") },
                        colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Text("Edit Profile",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.background)
                    }

                    Spacer(Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement
                        .spacedBy(10.dp)
                    )
                    {
                        Button(
                            onClick = {
                                profileViewModel.logOut()
                                Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
                                onGoHome()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
                            modifier = Modifier.width(100.dp)
                        ) {
                            Text("Log Out", color = MaterialTheme.colorScheme.background)
                        }

                    }
                }
            }

            // Extra space so the logo + bottom nav never cover last content
            Spacer(modifier = Modifier.height(24.dp))

            // Bottom logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outline_award_meal_24),
                    contentDescription = "App logo",
                    modifier = Modifier.size(120.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
