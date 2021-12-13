package dev.saurabhmishra.data.sources

import dev.saurabhmishra.domain.models.User

interface UserLocalSource {
    suspend fun saveUsers(users: List<User>)
    suspend fun deleteAllUsers()
    suspend fun getUserForId(id: Long): User?
}