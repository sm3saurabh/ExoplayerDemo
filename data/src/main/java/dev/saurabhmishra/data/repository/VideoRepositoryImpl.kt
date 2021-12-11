package dev.saurabhmishra.data.repository

import dev.saurabhmishra.data.sources.VideoLocalSource
import dev.saurabhmishra.data.sources.VideoRemoteSource
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class VideoRepositoryImpl(
    private val localSource: VideoLocalSource,
    private val remoteSource: VideoRemoteSource
): VideoRepository {

    override suspend fun loadAndSaveVideos(): List<VideoData> {
        TODO("Not yet implemented")
    }

    override fun getLocalVideos(): Flow<List<VideoData>> {
        TODO("Not yet implemented")
    }

    override fun getLocalVideoSuggestions(): Flow<List<VideoData>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLocalVideos() {
        TODO("Not yet implemented")
    }
}