package dev.saurabhmishra.exoplayersample.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.exoplayersample.base.BaseViewModel
import dev.saurabhmishra.exoplayersample.extensions.safeLaunch
import javax.inject.Inject

@HiltViewModel
class PlayerContentViewModel @Inject constructor(): BaseViewModel() {

    val viewState: LiveData<PlayerContentViewState> = MutableLiveData()


    fun loadPlayerContent() {
        viewModelScope.safeLaunch {

        }
    }


}

sealed class PlayerContentViewState {
    object Idle: PlayerContentViewState()
    class ExpandedComments(): PlayerContentViewState()
    class CollapsedComments(): PlayerContentViewState()
}