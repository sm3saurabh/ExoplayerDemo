package dev.saurabhmishra.exoplayersample.ui.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseActivity
import dev.saurabhmishra.exoplayersample.databinding.ActivityPlayerBinding
import dev.saurabhmishra.exoplayersample.extensions.nonNull
import javax.inject.Inject

@AndroidEntryPoint
class PlayerActivity: BaseActivity<PlayerViewModel, ActivityPlayerBinding>() {

    @Inject lateinit var player: ExoPlayer

    override fun layoutId(): Int {
        return R.layout.activity_player
    }

    override fun viewModelClass(): Class<PlayerViewModel> {
        return PlayerViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.loadData()
        }

        setupListeners()

        startObserving()
        binding.miniPlayer.player = player
    }

    private fun startObserving() {
        viewModel.viewState.nonNull().observe(this) { state ->
            when (state) {
                is PlayerViewState.Error -> showError(state)
                is PlayerViewState.PlayVideo -> setupPlayer(state)
                else -> {
                    // do nothing
                }
            }
            toggleLoading(state is PlayerViewState.Loading)
        }
    }

    private fun setupListeners() {
        binding.fullscreenButton.setOnClickListener {
            openFullScreen()
        }
    }

    private fun toggleLoading(show: Boolean) {
        with(binding) {
            progressBar.isVisible = show
            contentRecycler.isGone = show
            miniPlayer.isGone = show
            fullscreenButton.isGone = show
        }
    }

    private fun setupPlayer(state: PlayerViewState.PlayVideo) {
        binding.miniPlayer.requestFocus()
        player.setMediaItem(MediaItem.fromUri(state.currentVideo.videoData.videoUrl))

        player.prepare()
        player.play()
    }

    private fun showError(errorState: PlayerViewState.Error) {
        Toast.makeText(this, errorState.msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        if (viewModel.getIsFullScreen()) {
            closeFullScreen()
        } else {
            super.onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    private fun closeFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        if (supportActionBar != null) {
            supportActionBar?.show()
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val params = binding.miniPlayer.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = (200 * applicationContext.resources.displayMetrics.density).toInt()
        binding.miniPlayer.layoutParams = params
        viewModel.setIsFullScreen(false)
    }

    private fun openFullScreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val params = binding.miniPlayer.layoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding.miniPlayer.layoutParams = params
        viewModel.setIsFullScreen(true)
    }
}