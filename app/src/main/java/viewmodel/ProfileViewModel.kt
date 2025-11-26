package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.moodkitchen.data.AppDatabase
import com.example.moodkitchen.data.Profile
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
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        loadProfile()
    }

    fun saveProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            profileDao.insertProfile(profile)
            _profile.value = profileDao.getProfile()
            _isLoggedIn.value = true
        }
    }

    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _profile.value = profileDao.getProfile()

        }
    }
    init {
        loadProfile()
    }

    // Log in / Log out functions
    fun logIn() {
        _isLoggedIn.value = true
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.value = false
        }
    }
}
