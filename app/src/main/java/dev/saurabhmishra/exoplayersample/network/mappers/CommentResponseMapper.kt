package dev.saurabhmishra.exoplayersample.network.mappers

import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.network.model.CommentResponse

fun CommentResponse.toModel(): Comment {
   return Comment(
       commentId = this.commentId,
       videoId = this.videoId ?: 0,
       userId = this.userId ?: 0,
       commentContent = this.commentContent.orEmpty(),
       likeCount = this.likeCount ?: 0
   )
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        commentId, videoId, userId, commentContent, likeCount
    )
}