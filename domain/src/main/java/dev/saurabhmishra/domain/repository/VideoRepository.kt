package dev.saurabhmishra.domain.repository

import dev.saurabhmishra.domain.models.VideoData
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    suspend fun loadAndSaveVideos(): List<VideoData>

    fun getLocalVideos(): Flow<List<VideoData>>

    fun getLocalVideoSuggestions(): Flow<List<VideoData>>

    suspend fun deleteLocalVideos()
}