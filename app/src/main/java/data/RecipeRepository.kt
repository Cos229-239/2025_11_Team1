package com.example.moodkitchen_jenn.data

import com.example.moodkitchen_jenn.model.Recipe

object RecipeRepository {
    private val recipes = mapOf(
        "Happy" to listOf(
            Recipe("Berry Smoothie", "Sweet and refreshing start to your day."),
            Recipe("Mango Salsa Tacos", "Fresh, bright, and colorful.")
        ),
        "Tired" to listOf(
            Recipe("Avocado Toast", "Quick, energizing, and easy to make."),
            Recipe("Oat Latte", "Comfort in a cup.")
        ),
        "Cozy" to listOf(
            Recipe("Tomato Soup", "Warm and nostalgic."),
            Recipe("Mac and Cheese", "Soft, creamy comfort.")
        ),
        "Stressed" to listOf(
            Recipe("Green Tea", "Relax and breathe."),
            Recipe("Dark Chocolate Muffin", "A small indulgence to lift your mood.")
        )
    )

    fun getRecipesForMood(mood: String): List<Recipe> {
        return recipes[mood] ?: emptyList()
    }
}


