package dev.saurabhmishra.data.repository

import dev.saurabhmishra.data.sources.CommentsLocalSource
import dev.saurabhmishra.data.sources.CommentsRemoteSource
import dev.saurabhmishra.domain.SafeResult
import dev.saurabhmishra.domain.Wood
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.domain.repository.CommentsRepository
import kotlinx.coroutines.flow.Flow

class CommentRepositoryImpl(
    private val localSource: CommentsLocalSource,
    private val remoteSource: CommentsRemoteSource
): CommentsRepository {

    override suspend fun loadAndSaveComments() {

        when (val result = remoteSource.getAllComments()) {
            is SafeResult.Success -> {
                localSource.saveComments(result.data.orEmpty())
            }
            is SafeResult.Failure -> {
                Wood.error(result.msg, result.exception)
            }
        }
    }

    override fun getAllComments(): Flow<List<Comment>> {
        return localSource.getAllComments()
    }

    override fun getAllCommentsForCurrentVideo(currentVideoData: VideoData): Flow<List<Comment>> {
        return localSource.getAllCommentsForCurrentVideo(currentVideoData)
    }

    override suspend fun addComment(comment: Comment): Boolean {
        return try {
            localSource.addComment(comment)
            true
        } catch (ex: Exception) {
            false
        }
    }

    override suspend fun deleteComment(comment: Comment): Boolean {
        return try {
            localSource.deleteComment(comment)
            true
        } catch (ex: Exception) {
            false
        }
    }
}