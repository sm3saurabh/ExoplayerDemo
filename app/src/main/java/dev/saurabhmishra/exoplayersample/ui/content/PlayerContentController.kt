package dev.saurabhmishra.exoplayersample.ui.content

import com.airbnb.epoxy.TypedEpoxyController
import dev.saurabhmishra.exoplayersample.uimodel.UIModelPlayerContent
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideoComments
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideoSuggestions

class PlayerContentController: TypedEpoxyController<UIModelPlayerContent>() {



    override fun buildModels(data: UIModelPlayerContent?) {
        data?.let {
            buildItems(data)
        }
    }

    private fun buildItems(data: UIModelPlayerContent) {
        // 1. Build current video statistics like title, views,
        buildCurrentVideoStats(data.currentVideo)


        // 2. Build either expanded comments or video suggestions with comment header based on
        // whether comments section is in the expanded state
        if (data.videoComments.isExpanded) {
            buildExpandedComments(data.videoComments)
        } else {
            buildCommentHeaderAndSuggestions(data)
        }

    }

    private fun buildCommentHeaderAndSuggestions(data: UIModelPlayerContent) {

        // Build the comment section header.
        buildCommentsHeader(data.videoComments)

        // Build video suggestions
        buildVideoSuggestions(data.videoSuggestions)
    }

    private fun buildVideoSuggestions(videoSuggestions: UIModelVideoSuggestions) {

    }

    private fun buildCommentsHeader(videoComments: UIModelVideoComments) {

    }

    private fun buildExpandedComments(videoComments: UIModelVideoComments) {

    }

    private fun buildCurrentVideoStats(currentVideo: UIModelVideo) {

    }
}