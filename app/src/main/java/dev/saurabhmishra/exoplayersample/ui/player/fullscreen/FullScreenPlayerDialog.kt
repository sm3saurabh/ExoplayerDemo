package dev.saurabhmishra.exoplayersample.ui.player.fullscreen

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FullScreenPlayerDialog: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return object : Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            override fun onBackPressed() {

            }
        }
    }
}