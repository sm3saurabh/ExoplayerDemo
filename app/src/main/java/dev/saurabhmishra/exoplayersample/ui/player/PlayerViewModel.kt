package dev.saurabhmishra.exoplayersample.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.exoplayersample.base.BaseViewModel
import dev.saurabhmishra.exoplayersample.extensions.safeLaunch
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(

): BaseViewModel() {

    private var isFullScreen = false

    val viewState: LiveData<PlayerViewState> = MutableLiveData()

    fun loadData() {
        viewModelScope.safeLaunch {
            viewState.setValue(PlayerViewState.Loading)

        }
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