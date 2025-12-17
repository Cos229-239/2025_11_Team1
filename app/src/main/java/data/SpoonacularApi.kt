package com.example.moodkitchen.data


import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("includeIngredients") ingredients: String,
        @Query("number") number: Int = 10,
        @Query("addRecipeInformation") addInfo: Boolean = true
    ): SpoonacularResponse
}

data class SpoonacularResponse(
    val results: List<SpoonacularRecipe>
)

data class SpoonacularRecipe(
    val id: Int,
    val title: String,
    val image: String?,
    val summary: String?,
    val instructions: String?,
    val extendedIngredients: List<Ingredient>?
)

data class Ingredient(
    val original: String
)