package dev.saurabhmishra.data.sources

import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.models.Comment

interface CommentsRemoteSource {
    suspend fun getAllComments(): SafeResult<List<Comment>>
}