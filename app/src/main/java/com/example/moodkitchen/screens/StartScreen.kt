package com.example.moodkitchen.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodkitchen.R
import com.example.moodkitchen.ui.theme.OrangeSecondary

@Composable
fun StartScreen(onContinueClicked: () -> Unit, onProfileClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Name
        Text(
            text = "Mood Kitchen",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // App Icon
        Image(
            painter = painterResource( id = R.drawable.outline_award_meal_24), // put icon here
            contentDescription = "App logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 32.dp)
        )

        // Continue Button
        Button(
            onClick = onContinueClicked,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.8f) // slightly narrower looks nicer
        ) {
            Text(
                text = "Continue",
                fontSize = 18.sp
            )

        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onProfileClicked,
            colors = ButtonDefaults.buttonColors(OrangeSecondary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                "Create Profile",
                fontSize = 20.sp
            )

        }

    }
}