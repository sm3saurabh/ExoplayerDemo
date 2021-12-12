package dev.saurabhmishra.exoplayersample.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentResponse(
    val commentId: Long,
    val videoId: Long? = null,
    val userId: Long? = null,
    val commentContent: String? = null,
    val likeCount: Int? = null
)
