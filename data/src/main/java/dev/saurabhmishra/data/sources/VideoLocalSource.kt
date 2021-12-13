package dev.saurabhmishra.data.sources

import dev.saurabhmishra.domain.models.VideoData
import kotlinx.coroutines.flow.Flow

interface VideoLocalSource {
    suspend fun saveVideos(videoData: List<VideoData>)
    suspend fun getAllVideos(): List<VideoData>
    fun getAllVideosFlow(): Flow<List<VideoData>>
    fun getVideoSuggestions(currentVideoData: VideoData): Flow<List<VideoData>>
    suspend fun deleteLocalVideos()
    suspend fun getCurrentSelectedVideo(): VideoData?
    suspend fun setCurrentSelectedVideo(videoData: VideoData)
}