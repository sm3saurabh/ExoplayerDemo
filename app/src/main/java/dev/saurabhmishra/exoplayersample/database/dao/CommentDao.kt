package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.saurabhmishra.exoplayersample.database.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao: BaseDao<CommentEntity> {

    @Query("SELECT * FROM CommentEntity")
    suspend fun getAllComments(): List<CommentEntity>

    @Query("SELECT * FROM CommentEntity")
    fun getAllCommentsFlow(): Flow<List<CommentEntity>>

    @Query("DELETE FROM CommentEntity")
    suspend fun deleteAllComments()
}