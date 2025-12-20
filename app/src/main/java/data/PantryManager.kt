package com.example.moodkitchen.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PantryManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("pantry_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveIngredients(ingredients: List<String>) {
        val json = gson.toJson(ingredients)
        prefs.edit().putString("ingredients", json).apply()
    }

    fun getIngredients(): List<String> {
        val json = prefs.getString("ingredients", null) ?: return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }

    fun clearIngredients() {
        prefs.edit().remove("ingredients").apply()
    }
}

