package dev.saurabhmishra.exoplayersample.ui.content

import com.airbnb.epoxy.TypedEpoxyController
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.uimodel.UIModelPlayerContent
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideoComments
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideoSuggestions

class PlayerContentController: TypedEpoxyController<PlayerContentViewState>() {

    interface EventHandler {
        fun onCommentLiked(comment: Comment)
        fun onCommentDisliked(comment: Comment)
        fun onCommentExpanded()
        fun onCommentCollapsed()
        fun onVideoSelected(video: UIModelVideo)
    }


    override fun buildModels(data: PlayerContentViewState?) {
        data?.let {
            buildItems(data)
        }
    }

    private fun buildItems(state: PlayerContentViewState) {
        when (state) {
            is PlayerContentViewState.CollapsedComments -> {
                buildCommentHeaderAndSuggestions(state.uiModelPlayerContent)
            }
            is PlayerContentViewState.ExpandedComments -> {
                buildExpandedComments(state.uiModelPlayerContent)
            }
            PlayerContentViewState.Idle -> {
                // do nothing
            }
            PlayerContentViewState.Loading -> {
                buildLoader()
            }
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

    }

    private fun buildVideoSuggestions(videoSuggestions: UIModelVideoSuggestions) {

    }

    private fun buildCommentsHeader(videoComments: UIModelVideoComments) {

    }

    private fun buildExpandedComments(data: UIModelPlayerContent) {
        buildCurrentVideoStats(data.currentVideo)
    }

    private fun buildCurrentVideoStats(currentVideo: UIModelVideo) {

    }
}