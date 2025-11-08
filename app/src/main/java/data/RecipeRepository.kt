package com.example.moodkitchen_jenn.data

import com.example.moodkitchen_jenn.model.Recipe

object RecipeRepository {
    private val recipes = mapOf(
        "Happy" to listOf(
            Recipe("Berry Smoothie", "Sweet and refreshing start to your day."),
            Recipe("Mango Salsa Tacos", "Fresh, bright, and colorful."),
            Recipe("Ice Cream Sundae", "A delightful way to extend the good vibes."),
            Recipe("Sparkling Lemonade", "Fizzy, fun, and full of sunshine."),
            Recipe("Beef Tenderloin", "A hearty, feel-good meal that turns joy into flavor."),
            Recipe("Peach Cobbler", "A joyful treat that lifts the spirits.")
        ),
        "Tired" to listOf(
            Recipe("Avocado Toast", "Quick, energizing, and easy to make."),
            Recipe("Oat Latte", "Comfort in a cup."),
            Recipe("Chicken Noodle Soup", "Restorative and easy to digest."),
            Recipe("Mashed Potatoes with Butter", "Velvety smooth and made to soothe."),
            Recipe("Grilled Cheese Sandwich", "Simple, steady, and just right."),
            Recipe("Rice Porridge", "Mild flavor and kind to tired stomachs.")
        ),
        "Cozy" to listOf(
            Recipe("Tomato Soup", "Warm and nostalgic."),
            Recipe("Mac and Cheese", "Soft, creamy comfort."),
            Recipe("Caprese Salad", "Pure and light."),
            Recipe("Banana Pudding", "Mellow and gentle."),
            Recipe("Pancakes", "Fluffy and tender."),
            Recipe("Baked Beans", "A quiet, wholesome dish that satisfies the heart.")
        ),
        "Stressed" to listOf(
            Recipe("Green Tea", "Relax and breathe."),
            Recipe("Dark Chocolate Muffin", "A small indulgence to lift your mood."),
            Recipe("Hot Chocolate with Marshmallows", "Sweet, toasty and safe."),
            Recipe("Oatmeal with Honey", "Comforts the mind and eases tension."),
            Recipe("Salmon Teriyaki", "A warm, steady bite for frazzled nerves."),
            Recipe("Scrambled Eggs", "A humble, familiar dish that steadies the senses.")
        )
    )

    fun getRecipesForMood(mood: String): List<Recipe> {
        return recipes[mood] ?: emptyList()
    }
}


