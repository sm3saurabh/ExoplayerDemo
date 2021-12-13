package dev.saurabhmishra.data.sources

import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.models.VideoData
import kotlinx.coroutines.flow.Flow

interface CommentsLocalSource {
    suspend fun saveComments(comments: List<Comment>)
    fun getAllCommentsForCurrentVideo(currentVideoData: VideoData): Flow<List<Comment>>
    fun getAllComments(): Flow<List<Comment>>
    suspend fun deleteAllComments()
    suspend fun addComment(comment: Comment)
    suspend fun deleteComment(comment: Comment)
}