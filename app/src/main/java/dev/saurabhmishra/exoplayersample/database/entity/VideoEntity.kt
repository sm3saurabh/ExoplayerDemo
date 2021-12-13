package dev.saurabhmishra.exoplayersample.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey val videoId: Long,
    val videoUrl: String,
    val title: String,
    val views: Long,
    val userId: Long,
    val likeCount: Long,
    val dislikeCount: Long,
    val uploadedTimeStamp: Long
)