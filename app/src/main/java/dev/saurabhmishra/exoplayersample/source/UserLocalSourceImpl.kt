package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.UserLocalSource
import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.database.mappers.toEntity
import dev.saurabhmishra.exoplayersample.database.mappers.toModel
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UserLocalSourceImpl(
    private val db: ExoplayerSampleDB,
    private val executionContext: CoroutineContext
): UserLocalSource {

    override suspend fun saveUsers(users: List<User>) {
        withContext(executionContext) {
            val entities = users.map { user ->
                user.toEntity()
            }
            db.userDao().insertAll(entities)
        }
    }

    override suspend fun deleteAllUsers() {
        withContext(executionContext) {
            db.userDao().deleteAllUsers()
        }
    }

    override suspend fun getUserForId(id: Long): User? {
        return withContext(executionContext) {
            db.userDao().getUserForId(id)?.toModel()
        }
    }
}