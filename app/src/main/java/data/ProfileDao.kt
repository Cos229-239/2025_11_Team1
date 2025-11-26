package com.example.moodkitchen.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile)

    @Query("SELECT * FROM profiles LIMIT 1")
    suspend fun getProfile(): Profile?

    @Query("DELETE FROM profiles")
    suspend fun clearProfile()
}

