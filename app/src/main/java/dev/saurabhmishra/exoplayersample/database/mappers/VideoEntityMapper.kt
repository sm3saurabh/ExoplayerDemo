package dev.saurabhmishra.exoplayersample.database.mappers

import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.database.entity.VideoEntity

fun VideoEntity.toModel(): VideoData {
    return VideoData(
        videoId = this.videoId,
        videoUrl = this.videoUrl,
        title = this.title,
        views = this.views,
        userId = this.userId,
        likeCount = this.likeCount,
        dislikeCount = this.dislikeCount,
        uploadedTimeStamp = this.uploadedTimeStamp
    )
}

fun VideoData.toEntity(): VideoEntity {
    return VideoEntity(
        videoId = this.videoId,
        videoUrl = this.videoUrl,
        title = this.title,
        views = this.views,
        userId = this.userId,
        likeCount = this.likeCount,
        dislikeCount = this.dislikeCount,
        uploadedTimeStamp = this.uploadedTimeStamp
    )
}