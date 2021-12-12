package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.saurabhmishra.exoplayersample.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao: BaseDao<UserEntity> {

    @Query("SELECT * FROM UserEntity WHERE userId = :userId")
    suspend fun getUserForId(userId: Long): UserEntity?

    @Query("SELECT * FROM UserEntity")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM UserEntity")
    fun getAllUsersFlow(): Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAllUsers()
}
