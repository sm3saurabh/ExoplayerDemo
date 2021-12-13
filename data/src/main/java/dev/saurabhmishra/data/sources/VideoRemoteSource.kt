package dev.saurabhmishra.data.sources

import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.VideoData

interface VideoRemoteSource {
    suspend fun getVideosFromRemote(): SafeResult<List<VideoData>>
}