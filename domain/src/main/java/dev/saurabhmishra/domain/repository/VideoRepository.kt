package dev.saurabhmishra.domain.repository

import dev.saurabhmishra.domain.models.VideoData
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    suspend fun loadAndSaveVideos()

    fun getLocalVideos(): Flow<List<VideoData>>

    fun getLocalVideoSuggestions(currentVideoData: VideoData): Flow<List<VideoData>>

    suspend fun deleteLocalVideos()
}