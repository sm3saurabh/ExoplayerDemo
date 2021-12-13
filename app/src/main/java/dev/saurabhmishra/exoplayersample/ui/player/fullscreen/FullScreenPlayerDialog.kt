package dev.saurabhmishra.exoplayersample.ui.player.fullscreen

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import dev.saurabhmishra.exoplayersample.extensions.fragmentDelegate
import dev.saurabhmishra.exoplayersample.ui.player.PlayerFullscreenDelegate

class FullScreenPlayerDialog: DialogFragment() {

    private val playerDelegate by fragmentDelegate<PlayerFullscreenDelegate>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return object : Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            override fun onBackPressed() {
                playerDelegate?.dialogBackPressed()
            }
        }
    }
}