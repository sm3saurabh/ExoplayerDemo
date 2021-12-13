package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.CommentsLocalSource
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.database.mappers.toEntity
import dev.saurabhmishra.exoplayersample.database.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CommentsLocalSourceImpl(
    private val db: ExoplayerSampleDB,
    private val executionContext: CoroutineContext
): CommentsLocalSource {
    override suspend fun saveComments(comments: List<Comment>) {
        withContext(executionContext) {
            val entities = comments.map { comment ->
                comment.toEntity()
            }
            db.commentDao().insertAll(entities)
        }
    }

    override fun getAllCommentsForCurrentVideo(currentVideoData: VideoData): Flow<List<Comment>> {
        return db.commentDao().getAllCommentsForCurrentVideo(currentVideoData.videoId)
            .map { entities ->
                entities.map { entity ->
                    entity.toModel()
                }
            }.flowOn(executionContext)
    }

    override fun getAllComments(): Flow<List<Comment>> {
        return db.commentDao().getAllCommentsFlow()
            .map { entities ->
                entities.map { entity ->
                    entity.toModel()
                }
            }.flowOn(executionContext)
    }

    override suspend fun deleteAllComments() {
        withContext(executionContext) {
            db.commentDao().deleteAllComments()
        }
    }

    override suspend fun addComment(comment: Comment) {
       withContext(executionContext) {
           db.commentDao().insertViaReplace(comment.toEntity())
       }
    }

    override suspend fun deleteComment(comment: Comment) {
        withContext(executionContext) {
            db.commentDao().delete(comment.toEntity())
        }
    }
}