package dev.saurabhmishra.exoplayersample.ui.player

import android.os.Bundle
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseActivity
import dev.saurabhmishra.exoplayersample.databinding.ActivityPlayerBinding

class PlayerActivity: BaseActivity<PlayerViewModel, ActivityPlayerBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_player
    }

    override fun viewModelClass(): Class<PlayerViewModel> {
        return PlayerViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}