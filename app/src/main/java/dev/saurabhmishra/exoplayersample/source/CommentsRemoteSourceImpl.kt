package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.CommentsRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.network.Api
import dev.saurabhmishra.exoplayersample.network.mappers.toModel
import dev.saurabhmishra.exoplayersample.utils.safeApiCall
import kotlin.coroutines.CoroutineContext

class CommentsRemoteSourceImpl(
    private val api: Api,
    private val executionContext: CoroutineContext
): CommentsRemoteSource {

    override suspend fun getAllComments(): SafeResult<List<Comment>> {
        return safeApiCall(executionContext) {
            api.loadComments().map { response ->
                response.toModel()
            }
        }
    }
}