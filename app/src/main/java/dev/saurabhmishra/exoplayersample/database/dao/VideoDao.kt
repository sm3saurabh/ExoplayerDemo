package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.saurabhmishra.exoplayersample.database.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao: BaseDao<VideoEntity> {

    @Query("SELECT * FROM VideoEntity")
    suspend fun getAllVideos(): List<VideoEntity>

    @Query("SELECT * FROM VideoEntity WHERE videoId = :videoId")
    suspend fun getVideoForId(videoId: Long): VideoEntity?

    @Query("SELECT * FROM VideoEntity")
    fun getAllVideosFlow(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM VideoEntity WHERE videoId != :videoId")
    fun getVideoSuggestions(videoId: Long): Flow<List<VideoEntity>>

    @Query("DELETE FROM VideoEntity")
    suspend fun deleteAllVideos()
}