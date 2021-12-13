package dev.saurabhmishra.data.repository

import dev.saurabhmishra.data.sources.UserLocalSource
import dev.saurabhmishra.data.sources.UserRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.Wood
import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userLocalSource: UserLocalSource,
    private val userRemoteSource: UserRemoteSource
): UserRepository {

    override suspend fun loadAndSaveUsers() {
        when (val result = userRemoteSource.getUsers()) {
            is SafeResult.Success -> {
                userLocalSource.saveUsers(result.data.orEmpty())
            }
            is SafeResult.Failure -> {
                Wood.error(result.msg, result.exception)
            }
        }
    }

    override suspend fun deleteAllUsers() {
        userLocalSource.deleteAllUsers()
    }

    override suspend fun getUserForId(id: Long): User? {
        return userLocalSource.getUserForId(id)
    }
}