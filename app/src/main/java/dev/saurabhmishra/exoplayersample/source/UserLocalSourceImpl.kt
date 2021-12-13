package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.UserLocalSource
import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.database.mappers.toEntity
import dev.saurabhmishra.exoplayersample.database.mappers.toModel

class UserLocalSourceImpl(
    private val db: ExoplayerSampleDB
): UserLocalSource {

    override suspend fun saveUsers(users: List<User>) {
        val entities = users.map { user ->
            user.toEntity()
        }
        db.userDao().insertAll(entities)
    }

    override suspend fun deleteAllUsers() {
        db.userDao().deleteAllUsers()
    }

    override suspend fun getUserForId(id: Long): User? {
        return db.userDao().getUserForId(id)?.toModel()
    }
}