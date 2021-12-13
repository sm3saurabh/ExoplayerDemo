package dev.saurabhmishra.domain.repository

import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.models.VideoData
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {
    suspend fun loadAndSaveComments()
    fun getAllComments(): Flow<List<Comment>>
    fun getAllCommentsForCurrentVideo(currentVideoData: VideoData): Flow<List<Comment>>
    suspend fun addComment(comment: Comment): Boolean
    suspend fun deleteComment(comment: Comment): Boolean
}