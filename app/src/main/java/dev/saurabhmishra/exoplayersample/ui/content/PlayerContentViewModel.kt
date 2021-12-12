package dev.saurabhmishra.exoplayersample.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.exoplayersample.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerContentViewModel @Inject constructor(): BaseViewModel() {

    val viewState: LiveData<PlayerContentViewState> = MutableLiveData()



}

sealed class PlayerContentViewState {

}