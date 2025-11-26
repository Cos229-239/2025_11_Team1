package com.example.moodkitchen.screens



import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moodkitchen.R
import viewmodel.ProfileViewModel
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.TealPrimary


@Composable
fun OnboardingScreen(
    navController: NavHostController,
    onContinueClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    profileViewModel: ProfileViewModel
) {
    val profile by profileViewModel.profile.collectAsState()
    val isLoggedIn by profileViewModel.isLoggedIn.collectAsState()
    var showLoginDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Mood Kitchen", fontSize = 42.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 24.dp))
        Spacer(Modifier.height(16.dp))
        Image(painter = painterResource(id = R.drawable.outline_award_meal_24), contentDescription = "App logo", modifier = Modifier.size(300.dp).padding(bottom = 32.dp))
        Spacer(Modifier.height(16.dp))

        // LOG IN button
        Button(
            onClick = {
                if (profile != null) {
                    if (!isLoggedIn) {
                        showLoginDialog = true
                    } else {
                        Toast.makeText(context, "Account already logged in", Toast.LENGTH_SHORT).show()
                        navController.navigate("moodSelection")
                    }
                } else {
                    Toast.makeText(context, "No account found. Please create a profile first.", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Log In", fontSize = 18.sp)
        }

        Spacer(Modifier.height(16.dp))

        // CONTINUE button → always goes to moods
        Button(
            onClick = { navController.navigate("moodSelection") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Continue to Moods", fontSize = 18.sp)
        }

        Spacer(Modifier.height(16.dp))

        // CREATE PROFILE button → goes to profile screen
        Button(
            onClick = { navController.navigate("profileScreen") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TealPrimary),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Create Profile", fontSize = 18.sp)
        }

        // LOGIN DIALOG
        if (showLoginDialog && profile != null) {
            LoginDialog(
                profile = profile!!,
                onLoginSuccess = {
                    profileViewModel.logIn()
                    showLoginDialog = false
                    navController.navigate("moodSelection")
                },
                onCancel = { showLoginDialog = false }
            )
        }
    }
}