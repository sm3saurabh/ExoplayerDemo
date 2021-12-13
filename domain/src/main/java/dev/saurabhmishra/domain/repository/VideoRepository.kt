package dev.saurabhmishra.domain.repository

import dev.saurabhmishra.domain.models.VideoData
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    suspend fun loadAndSaveVideos()

    suspend fun getLocalVideos(): List<VideoData>

    fun getLocalVideosFlow(): Flow<List<VideoData>>

    fun getLocalVideoSuggestions(currentVideoData: VideoData): Flow<List<VideoData>>

    suspend fun deleteLocalVideos()

    suspend fun getCurrentSelectedVideo(): VideoData?

    suspend fun setCurrentSelectedVideo(videoData: VideoData)
}