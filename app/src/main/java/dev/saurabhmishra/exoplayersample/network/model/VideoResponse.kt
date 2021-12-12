package dev.saurabhmishra.exoplayersample.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoResponse(
    val videoId: Long,
    val videoUrl: String? = null,
    val title: String? = null,
    val views: Long? = null,
    val likeCount: Int? = null,
    val dislikeCount: Int? = null,
    val uploadedTimeStamp: Long? = null
)
