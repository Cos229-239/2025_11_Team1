package com.example.moodkitchen_jenn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodkitchen_jenn.ui.theme.OrangeSecondary
import com.example.moodkitchen_jenn.ui.theme.PeachBackground
import com.example.moodkitchen_jenn.ui.theme.TealPrimary

@Composable
fun MoodSelectionScreen(onMoodSelected: (String) -> Unit) {
    val moods = listOf("Happy", "Tired", "Cozy", "Stressed")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PeachBackground)   // use your background color
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How are you feeling today?",
            style = MaterialTheme.typography.headlineSmall,
            color = TealPrimary           // accent color for heading
        )
        Spacer(Modifier.height(20.dp))

        moods.forEach { mood ->
            Button(
                onClick = { onMoodSelected(mood) },
                colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),  // orange buttons
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(mood, color = PeachBackground) // text contrasting with button
            }
        }
    }
}
