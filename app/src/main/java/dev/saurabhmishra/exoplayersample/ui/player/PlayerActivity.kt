package dev.saurabhmishra.exoplayersample.ui.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseActivity
import dev.saurabhmishra.exoplayersample.databinding.ActivityPlayerBinding
import dev.saurabhmishra.exoplayersample.extensions.nonNull
import dev.saurabhmishra.exoplayersample.ui.player.fullscreen.FullScreenPlayerDialog
import javax.inject.Inject

class PlayerActivity: BaseActivity<PlayerViewModel, ActivityPlayerBinding>(), PlayerFullscreenDelegate {

    @Inject lateinit var player: ExoPlayer
    private val fullScreenDialog = FullScreenPlayerDialog()

    override fun layoutId(): Int {
        return R.layout.activity_player
    }

    override fun viewModelClass(): Class<PlayerViewModel> {
        return PlayerViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun toggleLoading(show: Boolean) {
        with(binding) {
            progressBar.isVisible = show
            contentRecycler.isGone = show
            miniPlayer.isGone = show
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

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun dialogBackPressed() {
        if (viewModel.getIsFullScreen()) {
            closeFullScreenDialog()
        }
    }

    private fun closeFullScreenDialog() {
        (binding.miniPlayer.parent as ViewGroup).removeView(binding.miniPlayer)
        ConstraintSet().apply {
            clone(binding.playerLayoutRoot)
            binding.playerLayoutRoot.addView(binding.miniPlayer, 0)
            connect(binding.miniPlayer.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            applyTo(binding.playerLayoutRoot)
        }
        viewModel.setIsFullScreen(false)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun openFullScreenDialog() {
        (binding.miniPlayer.parent as ViewGroup).removeView(binding.miniPlayer)
        fullScreenDialog.dialog?.addContentView(binding.miniPlayer, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        viewModel.setIsFullScreen(true)
        fullScreenDialog.show(supportFragmentManager, null)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}