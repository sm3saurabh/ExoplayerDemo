package dev.saurabhmishra.exoplayersample.uimodel

import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.domain.models.VideoData

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
        return videoData.views.toString()
    }
}

data class UIModelVideoComments(
    val comments: List<Comment>,
    val videoId: Long,
    val uniqueId: Long,
    val isExpanded: Boolean
)