package dev.saurabhmishra.data.sources

import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.User

interface UserRemoteSource {
    suspend fun getUsers(): SafeResult<List<User>>
}