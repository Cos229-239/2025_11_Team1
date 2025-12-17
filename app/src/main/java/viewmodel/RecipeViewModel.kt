package com.example.moodkitchen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodkitchen.data.RetrofitInstance
import com.example.moodkitchen.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val API_KEY = "8fb2d31ccc7841f1ae4d6fc06d2668bf"


    fun fetchRecipes(ingredients: List<String>) {
        if (ingredients.isEmpty()) {
            _recipes.value = emptyList()
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val ingredientsString = ingredients.joinToString(",")
                val response = RetrofitInstance.api.searchRecipes(
                    apiKey = API_KEY,
                    ingredients = ingredientsString,
                    number = 10
                )

                val convertedRecipes = response.results.map { spoonRecipe ->
                    Recipe(
                        name = spoonRecipe.title,
                        description = spoonRecipe.summary?.replace(Regex("<.*?>"), "") ?: "Delicious recipe",
                        ingredients = spoonRecipe.extendedIngredients?.map { it.original } ?: emptyList(),
                        directions = spoonRecipe.instructions?.replace(Regex("<.*?>"), "") ?: "Instructions not available",
                        imageRes = 0,
                        imageUrl = spoonRecipe.image
                    )
                }

                _recipes.value = convertedRecipes
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Failed to fetch recipes: ${e.message}"
                _recipes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
