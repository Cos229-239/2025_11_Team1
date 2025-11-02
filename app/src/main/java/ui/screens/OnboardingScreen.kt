package com.example.moodkitchen_jenn.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodkitchen_jenn.R

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
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
            painter = painterResource(id = R.drawable.outline_award_meal_24), // put icon here
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
    }
}
