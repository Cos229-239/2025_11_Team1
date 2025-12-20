package com.example.moodkitchen.data


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("includeIngredients") ingredients: String,
        @Query("number") number: Int = 10,
        @Query("addRecipeInformation") addInfo: Boolean = true
    ): SpoonacularResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): SpoonacularRecipe

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
    val extendedIngredients: List<Ingredient>?,
    val analyzedInstructions: List<Instruction>?
)

data class Ingredient(
    val original: String
)
data class Instruction(
    val name: String?,
    val steps: List<Step>
)

data class Step(
    val number: Int,
    val step: String
)