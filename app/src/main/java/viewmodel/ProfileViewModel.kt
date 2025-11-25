package com.example.moodkitchen.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "mood_kitchen_db"
    ).build()

    private val profileDao = db.profileDao()

    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: StateFlow<Profile?> = _profile

    // Tracks session login state
    val isLoggedIn = MutableStateFlow(false)

    init {
        loadProfile()
    }

    fun saveProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            profileDao.insertProfile(profile)
            _profile.value = profileDao.getProfile()
        }
    }

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _profile.value = profileDao.getProfile()
        }
    }

    fun logIn() {
        isLoggedIn.value = true
    }

    fun logOut() {
        isLoggedIn.value = false
    }
}
