package dev.saurabhmishra.exoplayersample.ui.content

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseFragment
import dev.saurabhmishra.exoplayersample.databinding.FragmentPlayerContentBinding
import dev.saurabhmishra.exoplayersample.extensions.nonNull

// Comments
// Video suggestions
// Option to add comment
class PlayerContentFragment : BaseFragment<PlayerContentViewModel, FragmentPlayerContentBinding>() {


    private lateinit var controller: PlayerContentController

    override fun layoutId(): Int {
        return R.layout.fragment_player_content
    }

    override fun viewModelClass(): Class<PlayerContentViewModel> {
        return PlayerContentViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupController()
        setupRecycler()
        startObserving()
    }

    private fun setupController() {
        controller = PlayerContentController()
    }

    private fun setupRecycler() {
        binding.playerContentRecycler.run {
            adapter = controller.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun startObserving() {
        viewModel.viewState.nonNull().observe(viewLifecycleOwner) { viewState ->
            controller.setData(viewState)
        }
    }
}