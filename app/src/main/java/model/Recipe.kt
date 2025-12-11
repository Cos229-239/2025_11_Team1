package com.example.moodkitchen.model

data class Recipe(
    val name: String,
    val description: String,
    val ingredients: List<String> = emptyList(),
    val directions: String = "",
    val imageRes: Int = 0,
    val imageUrl: String? = null
)


