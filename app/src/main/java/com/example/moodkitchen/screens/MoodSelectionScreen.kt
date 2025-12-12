package com.example.moodkitchen.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodkitchen.ui.theme.OrangeSecondary
import com.example.moodkitchen.ui.theme.PeachBackground
import com.example.moodkitchen.ui.theme.TealPrimary
import androidx.navigation.NavHostController
import androidx.compose.material3.Scaffold
import com.example.moodkitchen.screens.BottomNavigationBar

data class Mood(val emoji: String, val label: String)

@Composable
fun MoodSelectionScreen(
    navController: NavHostController,
    onMoodSelected: (String) -> Unit,
    onGoHome: () -> Unit,
    onProfileClicked: () -> Unit
) {

    val moods = listOf(
        Mood("😊", "Happy"),
        Mood("🥱", "Tired"),
        Mood("🍵", "Cozy"),
        Mood("😤", "Stressed")
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "moodSelection"
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PeachBackground)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "How are you feeling today?",
                style = MaterialTheme.typography.headlineSmall,
                color = TealPrimary
            )
            Spacer(Modifier.height(20.dp))

            moods.forEach { mood ->
                Button(
                    onClick = { onMoodSelected(mood.label) }, // only send label, not emoji
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Text("${mood.emoji} ${mood.label}", color = PeachBackground)
                }
            }
        }
    }
}