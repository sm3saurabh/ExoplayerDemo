package dev.saurabhmishra.exoplayersample.ui.player

import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayer
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseActivity
import dev.saurabhmishra.exoplayersample.databinding.ActivityPlayerBinding
import javax.inject.Inject

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



        player.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}