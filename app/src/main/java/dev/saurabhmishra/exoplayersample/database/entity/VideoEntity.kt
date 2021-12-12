package dev.saurabhmishra.exoplayersample.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey val videoId: Long,
    val videoUrl: String,
    val title: String,
    val views: Long,
    val likeCount: Int,
    val dislikeCount: Int,
    val uploadedTimeStamp: Long
)