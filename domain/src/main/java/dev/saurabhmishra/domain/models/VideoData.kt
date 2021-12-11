package dev.saurabhmishra.domain.models

data class VideoData(
    val videoId: Long,
    val videoUrl: String,
    val title: String,
    val likeCount: Int,
    val dislikeCount: Int,
    val uploadedTimeStamp: Long
)
