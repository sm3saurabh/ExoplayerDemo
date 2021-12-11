package dev.saurabhmishra.domain.models

data class Comment(
    val commentId: Long,
    val videoId: Long,
    val commentContent: String,
    val likeCount: Int
)
