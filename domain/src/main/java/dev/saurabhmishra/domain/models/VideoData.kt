package dev.saurabhmishra.domain.models

data class VideoData(
    val videoId: Long,
    val videoUrl: String,
    val title: String,
    val views: Long,
    val likeCount: Long,
    val dislikeCount: Long,
    val uploadedTimeStamp: Long
)
