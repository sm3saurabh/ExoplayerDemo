package dev.saurabhmishra.data.repository

import dev.saurabhmishra.data.sources.CommentsLocalSource
import dev.saurabhmishra.data.sources.CommentsRemoteSource
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.repository.CommentsRepository
import kotlinx.coroutines.flow.Flow

class CommentRepositoryImpl(
    private val localSource: CommentsLocalSource,
    private val remoteSource: CommentsRemoteSource
): CommentsRepository {

    override suspend fun loadAndSaveComments() {
        TODO("Not yet implemented")
    }

    override fun getAllComments(): Flow<List<Comment>> {
        TODO("Not yet implemented")
    }

    override suspend fun addComment(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteComment(): Boolean {
        TODO("Not yet implemented")
    }
}