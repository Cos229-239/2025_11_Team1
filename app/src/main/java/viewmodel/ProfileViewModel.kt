package com.example.moodkitchen.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "mood_kitchen_db"
    ).build()

    private val profileDao = db.profileDao()

    fun saveProfile(profile: Profile, onDone: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            profileDao.insertProfile(profile)

            withContext(Dispatchers.Main) {
                onDone()
            }
        }
    }

    suspend fun getProfile(): Profile? {
        val p = profileDao.getProfile()
        Log.d("ProfileViewModel", "Loaded profile: $p")
        return p
    }
}
