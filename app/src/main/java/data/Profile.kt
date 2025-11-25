package com.example.moodkitchen.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val name: String,
    val email: String,
    val password: String,
    val bio: String,
    val allergies: String = "",
    val favorites: String = ""
)


