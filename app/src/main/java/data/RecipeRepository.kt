package data/*package data
import com.example.moodkitchen.data.RetrofitInstance
import com.example.moodkitchen.model.Recipe



object RecipeRepository {

    suspend fun getRecipeDetails(
        recipeId: Int,
        apiKey: String
    ): Recipe {

        val spoonRecipe = RetrofitInstance.api.getRecipeDetails(
            id = recipeId,
            apiKey = apiKey
        )

        val directions =
            if (!spoonRecipe.analyzedInstructions.isNullOrEmpty()) {
                spoonRecipe.analyzedInstructions.first().steps.joinToString("\n") {
                    "${it.number}. ${it.step}"
                }
            } else {
                spoonRecipe.instructions?.replace(Regex("<.*?>"), "")
                    ?: "Instructions not available"
            }

        return Recipe(
            name = spoonRecipe.title,
            description = spoonRecipe.summary
                ?.replace(Regex("<.*?>"), "")
                ?: "Delicious recipe",
            ingredients = spoonRecipe.extendedIngredients
                ?.map { it.original }
                ?: emptyList(),
            directions = directions,
            imageRes = 0,
            imageUrl = spoonRecipe.image
        )
    }
}
*/