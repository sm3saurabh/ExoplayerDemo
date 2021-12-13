package dev.saurabhmishra.exoplayersample.ui.content

import com.airbnb.epoxy.TypedEpoxyController
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.commentContent
import dev.saurabhmishra.exoplayersample.commentsHeader
import dev.saurabhmishra.exoplayersample.currentVideoStats
import dev.saurabhmishra.exoplayersample.uimodel.UIModelPlayerContent
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideoComments
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideoSuggestions
import dev.saurabhmishra.exoplayersample.videoItem

class PlayerContentController(
    private val eventHandler: EventHandler
): TypedEpoxyController<PlayerContentViewState>() {

    interface EventHandler {
        fun onCommentLiked(comment: Comment)
        fun onCommentDisliked(comment: Comment)
        fun onCommentExpanded()
        fun onCommentCollapsed()
        fun onVideoSelected(video: UIModelVideo)
        fun onVideoLiked(currentVideo: UIModelVideo)
        fun onVideoDisliked(currentVideo: UIModelVideo)
        fun onVideoShared(currentVideo: UIModelVideo)
        fun onVideoDownloadRequested(currentVideo: UIModelVideo)
    }


    override fun buildModels(data: PlayerContentViewState?) {
        data?.let {
            buildItems(data)
        }
    }

    private fun buildItems(state: PlayerContentViewState) {
        when (state) {
            is PlayerContentViewState.PlayerContent -> buildPlayerContent(state)
            PlayerContentViewState.Idle -> {
                // do nothing
            }
            PlayerContentViewState.Loading -> {
                buildLoader()
            }
        }
    }

    private fun buildPlayerContent(state: PlayerContentViewState.PlayerContent) {
        if (state.isCommentExpanded) {
            buildExpandedComments(state.uiModelPlayerContent)
        } else {
            buildCommentHeaderAndSuggestions(state.uiModelPlayerContent)
        }
    }

    private fun buildCommentHeaderAndSuggestions(data: UIModelPlayerContent) {

        // build current video stats
        buildCurrentVideoStats(data.currentVideo)

        // Build the comment section header.
        buildCommentsHeader(data.videoComments)

        // Build video suggestions
        buildVideoSuggestions(data.videoSuggestions)
    }

    private fun buildLoader() {
        // See if this is actually required
    }

    private fun buildVideoSuggestions(videoSuggestions: UIModelVideoSuggestions) {

        videoSuggestions.suggestions.forEach { videoModel ->
            videoItem {
                id(videoModel.uniqueId)
                video(videoModel)
                videoItemClickListener { _ ->
                    this@PlayerContentController.eventHandler.onVideoSelected(videoModel)
                }
            }
        }

    }

    private fun buildCommentsHeader(videoComments: UIModelVideoComments) {
        commentsHeader {
            comments(videoComments)
            id(videoComments.uniqueId)
            commentExpandedClickListener { _ ->
                this@PlayerContentController.eventHandler.onCommentExpanded()
            }
        }
    }

    private fun buildExpandedComments(data: UIModelPlayerContent) {
        buildCurrentVideoStats(data.currentVideo)

        data.videoComments.comments.forEach { commentModel ->
            commentContent {
                id(commentModel.uniqueId)
                comment(commentModel)
            }
        }
    }

    private fun buildCurrentVideoStats(currentVideo: UIModelVideo) {
        currentVideoStats {
            id(currentVideo.uniqueId)
            video(currentVideo)

            dislikesClickListener {_ ->
                this@PlayerContentController.eventHandler.onVideoDisliked(currentVideo)
            }

            likesClickListener {_ ->
                this@PlayerContentController.eventHandler.onVideoLiked(currentVideo)
            }

            shareClickListener {_ ->
                this@PlayerContentController.eventHandler.onVideoShared(currentVideo)
            }

            downloadClickListener {_ ->
                this@PlayerContentController.eventHandler.onVideoDownloadRequested(currentVideo)
            }
        }
    }
}