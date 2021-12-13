package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.UserRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.exoplayersample.network.Api
import dev.saurabhmishra.exoplayersample.network.mappers.toModel
import dev.saurabhmishra.exoplayersample.utils.safeApiCall
import kotlin.coroutines.CoroutineContext

class UserRemoteSourceImpl(private val api: Api, private val executionContext: CoroutineContext): UserRemoteSource {

    override suspend fun getUsers(): SafeResult<List<User>> {
        return safeApiCall(executionContext = executionContext) {
            api.loadUsers().map { response ->
                response.toModel()
            }
        }
    }
}