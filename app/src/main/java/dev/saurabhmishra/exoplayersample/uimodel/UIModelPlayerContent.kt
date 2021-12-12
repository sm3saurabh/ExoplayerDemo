package dev.saurabhmishra.exoplayersample.uimodel

import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.extensions.formatNumberWithAbbr
import dev.saurabhmishra.exoplayersample.extensions.formatVideoTime

data class UIModelPlayerContent(
    val videoSuggestions: UIModelVideoSuggestions,
    val videoComments: UIModelVideoComments,
    val currentVideo: UIModelVideo
)

data class UIModelVideoSuggestions(
    val suggestions: List<UIModelVideo>,
    val uniqueId: Long
)

data class UIModelVideo(
    val uniqueId: Long,
    val videoData: VideoData
) {
    fun formatCurrentViews(): String {
        return videoData.views.formatNumberWithAbbr().orEmpty()
    }

    fun formatLikes(): String {
        return videoData.likeCount.formatNumberWithAbbr().orEmpty()
    }

    fun formatDislikes(): String {
        return videoData.dislikeCount.formatNumberWithAbbr().orEmpty()
    }

    fun formatTime(): String {
        return videoData.uploadedTimeStamp.formatVideoTime()
    }
}

data class UIModelVideoComments(
    val comments: List<UIModelComment>,
    val videoId: Long,
    val uniqueId: Long
) {
    fun numberOfComments(): String {
        return comments.size.toLong().formatNumberWithAbbr().orEmpty()
    }
}

data class UIModelComment(
    val uniqueId: Long,
    val comment: Comment,
    val userName: String
)