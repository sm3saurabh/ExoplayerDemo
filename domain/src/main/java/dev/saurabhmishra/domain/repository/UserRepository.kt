package dev.saurabhmishra.domain.repository

import dev.saurabhmishra.domain.models.User

interface UserRepository {
    suspend fun loadAndSaveUsers()
    suspend fun deleteAllUsers()

    suspend fun getUserForId(id: Long): User?
}