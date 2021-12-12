package dev.saurabhmishra.domain.repository

import dev.saurabhmishra.domain.models.Comment
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {
    suspend fun loadAndSaveComments()
    fun getAllComments(): Flow<List<Comment>>
    suspend fun addComment(): Boolean
    suspend fun deleteComment(): Boolean
}