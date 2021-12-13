package dev.saurabhmishra.exoplayersample.uimodel

import dev.saurabhmishra.domain.models.Comment
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

fun Comment.toUIModel(user: User): UIModelComment {
    return UIModelComment(
        uniqueId = UniqueIdGenerator.generateId(),
        comment = this,
        userName = user.userName
    )
}

fun List<UIModelVideo>.toPlayerContentModel(comments: List<UIModelComment>, currentVideo: UIModelVideo): UIModelPlayerContent {
    return UIModelPlayerContent(
        videoSuggestions = UIModelVideoSuggestions(
            suggestions = this,
            uniqueId = UniqueIdGenerator.generateId()
        ),
        videoComments = UIModelVideoComments(
            comments = comments,
            uniqueId = UniqueIdGenerator.generateId(),
            videoId = currentVideo.videoData.videoId
        ),
        currentVideo = currentVideo
    )
}