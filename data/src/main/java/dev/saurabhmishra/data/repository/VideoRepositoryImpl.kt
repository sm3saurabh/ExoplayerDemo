package dev.saurabhmishra.data.repository

import dev.saurabhmishra.data.sources.VideoLocalSource
import dev.saurabhmishra.data.sources.VideoRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.Wood
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class VideoRepositoryImpl(
    private val localSource: VideoLocalSource,
    private val remoteSource: VideoRemoteSource
): VideoRepository {

    override suspend fun loadAndSaveVideos() {

        when (val result = remoteSource.getVideosFromRemote()) {
            is SafeResult.Success -> {
                localSource.saveVideos(result.data.orEmpty())
            }
            is SafeResult.Failure -> {
                Wood.error(result.msg, result.exception)
            }
        }
    }

    override fun getLocalVideos(): Flow<List<VideoData>> {
        return localSource.getAllVideosFlow()
    }

    override fun getLocalVideoSuggestions(currentVideoData: VideoData): Flow<List<VideoData>> {
        return localSource.getVideoSuggestions(currentVideoData)
    }

    override suspend fun deleteLocalVideos() {
        localSource.deleteLocalVideos()
    }
}