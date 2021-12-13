package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.VideoRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.network.Api
import dev.saurabhmishra.exoplayersample.network.mappers.toModel
import dev.saurabhmishra.exoplayersample.utils.safeApiCall
import kotlin.coroutines.CoroutineContext

class VideoRemoteSourceImpl(
    private val api: Api,
    private val executionContext: CoroutineContext
): VideoRemoteSource {

    override suspend fun getVideosFromRemote(): SafeResult<List<VideoData>> {
        return safeApiCall(executionContext) {
            api.loadVideos().map { response ->
                response.toModel()
            }
        }
    }
}