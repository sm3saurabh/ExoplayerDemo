package dev.saurabhmishra.exoplayersample.ui.content

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseFragment
import dev.saurabhmishra.exoplayersample.databinding.FragmentPlayerContentBinding
import dev.saurabhmishra.exoplayersample.extensions.nonNull
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo

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
        controller = PlayerContentController(object : PlayerContentController.EventHandler {
            override fun onCommentLiked(comment: Comment) {
                TODO("Not yet implemented")
            }

            override fun onCommentDisliked(comment: Comment) {
                TODO("Not yet implemented")
            }

            override fun onCommentExpanded() {
                TODO("Not yet implemented")
            }

            override fun onCommentCollapsed() {
                TODO("Not yet implemented")
            }

            override fun onVideoSelected(video: UIModelVideo) {
                TODO("Not yet implemented")
            }

            override fun onVideoLiked(currentVideo: UIModelVideo) {
                TODO("Not yet implemented")
            }

            override fun onVideoDisliked(currentVideo: UIModelVideo) {
                TODO("Not yet implemented")
            }

            override fun onVideoShared(currentVideo: UIModelVideo) {
                TODO("Not yet implemented")
            }

            override fun onVideoDownloadRequested(currentVideo: UIModelVideo) {
                TODO("Not yet implemented")
            }

        })
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