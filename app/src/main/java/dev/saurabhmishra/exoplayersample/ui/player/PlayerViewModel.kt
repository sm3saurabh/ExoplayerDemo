package dev.saurabhmishra.exoplayersample.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.domain.repository.CommentsRepository
import dev.saurabhmishra.domain.repository.UserRepository
import dev.saurabhmishra.domain.repository.VideoRepository
import dev.saurabhmishra.exoplayersample.base.BaseViewModel
import dev.saurabhmishra.exoplayersample.extensions.safeLaunch
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import dev.saurabhmishra.exoplayersample.uimodel.toUIModel
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val videoRepository: VideoRepository,
    private val commentsRepository: CommentsRepository,
    private val userRepository: UserRepository
): BaseViewModel() {

    private var isFullScreen = false

    val viewState: LiveData<PlayerViewState> = MutableLiveData()

    fun loadData() {
        viewModelScope.safeLaunch {
            viewState.setValue(PlayerViewState.Loading)
            loadDataFromRemote()
            getSelectedVideo()
        }
    }

    private suspend fun getSelectedVideo() {
        val video = videoRepository.getCurrentSelectedVideo()
        if (video == null) {
            getFirstSelectedVideo()
        } else {
            setStateToPlayVideo(video)
        }
    }

    private suspend fun getFirstSelectedVideo() {
        val videos = videoRepository.getLocalVideos()
        val video = videos[0]
        videoRepository.setCurrentSelectedVideo(video)
        setStateToPlayVideo(video)
    }

    private suspend fun setStateToPlayVideo(video: VideoData) {
        val user = userRepository.getUserForId(video.userId)

        if (user != null) {
            viewState.setValue(PlayerViewState.PlayVideo(video.toUIModel(user)))
        }
    }

    private suspend fun loadDataFromRemote() {
        videoRepository.loadAndSaveVideos()
        commentsRepository.loadAndSaveComments()
        userRepository.loadAndSaveUsers()
    }

    fun getIsFullScreen() = isFullScreen
    fun setIsFullScreen(value: Boolean) {
        isFullScreen = value
    }

}

sealed class PlayerViewState {
    object Idle: PlayerViewState()
    object Loading: PlayerViewState()
    class PlayVideo(val currentVideo: UIModelVideo): PlayerViewState()
    class Error(val msg: String, val exception: Exception): PlayerViewState()
}