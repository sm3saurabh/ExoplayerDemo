package dev.saurabhmishra.exoplayersample.ui.content

import android.os.Bundle
import android.view.View
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseFragment
import dev.saurabhmishra.exoplayersample.databinding.FragmentPlayerContentBinding

// Comments
// Video suggestions
// Option to add comment
class PlayerContentFragment: BaseFragment<PlayerContentViewModel, FragmentPlayerContentBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_player_content
    }

    override fun viewModelClass(): Class<PlayerContentViewModel> {
        return PlayerContentViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}