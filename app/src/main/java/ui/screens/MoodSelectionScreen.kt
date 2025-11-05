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

data class Mood(val emoji: String, val label: String)

@Composable
fun MoodSelectionScreen(onMoodSelected: (String) -> Unit) {

    val moods = listOf(
        Mood("😊", "Happy"),
        Mood("🥱", "Tired"),
        Mood("🍵", "Cozy"),
        Mood("😤", "Stressed")
    )

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
                // 4️⃣ Show both emoji and label in button text
                Text("${mood.emoji} ${mood.label}", color = PeachBackground)
            }
        }
    }
}
