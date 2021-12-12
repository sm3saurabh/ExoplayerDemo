package dev.saurabhmishra.exoplayersample.network.mappers

import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.network.model.VideoResponse

fun VideoResponse.toModel(): VideoData {
    return VideoData(
        videoId = this.videoId,
        videoUrl = this.videoUrl.orEmpty(),
        title = this.title.orEmpty(),
        likeCount = this.likeCount ?: 0,
        views = this.views ?: 0,
        dislikeCount = this.dislikeCount ?: 0,
        uploadedTimeStamp = this.uploadedTimeStamp ?: 0
    )
}

fun VideoData.toResponse() : VideoResponse {
    return VideoResponse(
        videoId, videoUrl, title, views, likeCount, dislikeCount, uploadedTimeStamp
    )
}