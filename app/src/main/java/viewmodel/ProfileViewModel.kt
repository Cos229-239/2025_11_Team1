package com.example.moodkitchen.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "mood_kitchen_db"
    ).build()

    private val profileDao = db.profileDao()

    fun saveProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            profileDao.insertProfile(profile)
        }
    }

    suspend fun getProfile(): Profile? {
        return profileDao.getProfile()
    }
}
