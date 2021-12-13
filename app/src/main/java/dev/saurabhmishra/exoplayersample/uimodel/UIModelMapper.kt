package dev.saurabhmishra.exoplayersample.uimodel

import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.utils.UniqueIdGenerator

fun VideoData.toUIModel(user: User): UIModelVideo {
    return UIModelVideo(
        uniqueId = UniqueIdGenerator.generateId(),
        videoData = this,
        user = user
    )
}