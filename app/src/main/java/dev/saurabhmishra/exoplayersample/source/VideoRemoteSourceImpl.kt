package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.VideoRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.network.Api
import dev.saurabhmishra.exoplayersample.network.mappers.toModel
import dev.saurabhmishra.exoplayersample.utils.safeApiCall

class VideoRemoteSourceImpl(
    private val api: Api
): VideoRemoteSource {

    override suspend fun getVideosFromRemote(): SafeResult<List<VideoData>> {
        return safeApiCall {
            api.loadVideos().map { response ->
                response.toModel()
            }
        }
    }
}