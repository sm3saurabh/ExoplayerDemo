package dev.saurabhmishra.exoplayersample.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.domain.repository.CommentsRepository
import dev.saurabhmishra.domain.repository.UserRepository
import dev.saurabhmishra.domain.repository.VideoRepository
import dev.saurabhmishra.exoplayersample.base.BaseViewModel
import dev.saurabhmishra.exoplayersample.extensions.safeLaunch
import dev.saurabhmishra.exoplayersample.uimodel.UIModelPlayerContent
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import dev.saurabhmishra.exoplayersample.uimodel.toPlayerContentModel
import dev.saurabhmishra.exoplayersample.uimodel.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class PlayerContentViewModel @Inject constructor(
    private val commentsRepository: CommentsRepository,
    private val userRepository: UserRepository,
    private val videoRepository: VideoRepository
): BaseViewModel() {

    val viewState: LiveData<PlayerContentViewState> = MutableLiveData()

    private val loadedContent = AtomicBoolean(false)


    fun loadPlayerContent() {
        viewModelScope.safeLaunch {
            videoRepository.getLocalVideosFlow().collect { videos ->
                // Start the process when some videos are loaded in table
                if (!loadedContent.get() && videos.isNotEmpty()) {
                   startLoadingContent()
                }
            }
        }
    }

    private suspend fun startLoadingContent() {

        val currentVideo = getCurrentSelectedVideo()

        currentVideo?.let {
            actuallyLoadContent(currentVideo)
        }

        loadedContent.set(true)
    }

    private suspend fun getCurrentSelectedVideo(): UIModelVideo? {

        val currentVideo = videoRepository.getCurrentSelectedVideo()
        return currentVideo?.let {
            val user = userRepository.getUserForId(currentVideo.userId)
            user?.let {
               currentVideo.toUIModel(user)
            }
        }

    }

    private fun actuallyLoadContent(currentVideo: UIModelVideo) {
        viewModelScope.safeLaunch {
            val playerContentFlow = getPlayerContentFlow(currentVideo)


            playerContentFlow.collect { playerContent ->
                viewState.setValue(PlayerContentViewState.PlayerContent(playerContent, false))
            }
        }
    }

    private fun getPlayerContentFlow(currentVideo: UIModelVideo): Flow<UIModelPlayerContent> {
        return videoRepository.getLocalVideoSuggestions(currentVideoData = currentVideo.videoData)
            .zip(
                commentsRepository.getAllCommentsForCurrentVideo(currentVideoData = currentVideo.videoData)
            ) { videos, comments ->

                val videoUiModel = videos.mapNotNull { videoData ->
                    val user = userRepository.getUserForId(videoData.userId)
                    user?.let {
                        videoData.toUIModel(user)
                    }
                }

                val commentsUiModel = comments.mapNotNull { comment ->

                    val user = userRepository.getUserForId(comment.userId)
                    user?.let {
                        comment.toUIModel(user)
                    }
                }

                videoUiModel.toPlayerContentModel(commentsUiModel, currentVideo)
            }
    }


}

sealed class PlayerContentViewState {
    object Idle: PlayerContentViewState()
    object Loading: PlayerContentViewState()
    class PlayerContent(val uiModelPlayerContent: UIModelPlayerContent, val isCommentExpanded: Boolean): PlayerContentViewState()
}