package com.example.moodkitchen.data

class ProfileRepository(private val dao: ProfileDao) {
    suspend fun insert(profile: Profile) = dao.insertProfile(profile)
    suspend fun getProfile() = dao.getProfile()
    suspend fun update(profile: Profile) = dao.updateProfile(profile)
}
