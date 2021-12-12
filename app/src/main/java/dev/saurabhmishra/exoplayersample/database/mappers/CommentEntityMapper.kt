package dev.saurabhmishra.exoplayersample.database.mappers

import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.database.entity.CommentEntity

fun CommentEntity.toModel(): Comment {
    return Comment(
        videoId = this.videoId,
        likeCount = this.likeCount,
        commentId = this.commentId,
        commentContent = this.commentContent
    )
}

fun Comment.toEntity(): CommentEntity {
    return CommentEntity(
        videoId = this.videoId,
        likeCount = this.likeCount,
        commentId = this.commentId,
        commentContent = this.commentContent
    )
}