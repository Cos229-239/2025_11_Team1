package viewmodel

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

    private val API_KEY = "6a420ad3bbbf4457a9614b251a492798"


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
                        id = spoonRecipe.id,
                        name = spoonRecipe.title,
                        description = spoonRecipe.summary?.replace(Regex("<.*?>"), "")
                            ?: "Delicious recipe",
                        ingredients = spoonRecipe.extendedIngredients?.map { it.original }
                            ?: emptyList(),
                        directions = spoonRecipe.instructions?.replace(Regex("<.*?>"), "")
                            ?: "Instructions not available",
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

    suspend fun fetchRecipeById(id: Int): Recipe? {
        return try {
            // Fetch full recipe info
            val response = RetrofitInstance.api.getRecipeInformation(
                id,
                API_KEY
            )

            // Extract directions
            val directions = if (!response.analyzedInstructions.isNullOrEmpty()) {
                // Join all steps as plain text
                response.analyzedInstructions.first().steps.joinToString("\n") { it.step }
            } else {
                // fallback
                response.instructions?.replace(Regex("<.*?>"), "") ?: "Directions not available"
            }

            // Build Recipe object with only ingredients and directions
            Recipe(
                id = response.id,
                name = response.title,
                description = "",
                ingredients = response.extendedIngredients?.map { it.original } ?: emptyList(),
                directions = directions,
                imageUrl = response.image
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}