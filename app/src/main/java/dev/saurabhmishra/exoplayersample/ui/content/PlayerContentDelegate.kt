package dev.saurabhmishra.exoplayersample.ui.content

import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo

interface PlayerContentDelegate {
    fun onVideoSelected(currentVideo: UIModelVideo)
}