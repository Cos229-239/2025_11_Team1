package com.example.moodkitchen.data

class ProfileRepository(private val dao: ProfileDao) {
    suspend fun save(profile: Profile) = dao.insertProfile(profile) // handles insert or update
    suspend fun getProfile(): Profile? = dao.getProfile()
    suspend fun clearProfile() = dao.clearProfile()
}
