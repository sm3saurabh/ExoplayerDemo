package dev.saurabhmishra.exoplayersample.ui.content

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.saurabhmishra.domain.models.Comment
import dev.saurabhmishra.exoplayersample.R
import dev.saurabhmishra.exoplayersample.base.BaseFragment
import dev.saurabhmishra.exoplayersample.databinding.FragmentPlayerContentBinding
import dev.saurabhmishra.exoplayersample.extensions.nonNull
import dev.saurabhmishra.exoplayersample.uimodel.UIModelVideo
import dev.saurabhmishra.domain.Wood
import dev.saurabhmishra.exoplayersample.extensions.fragmentDelegate

// Comments
// Video suggestions
// Option to add comment
@AndroidEntryPoint
class PlayerContentFragment : BaseFragment<PlayerContentViewModel, FragmentPlayerContentBinding>() {


    private lateinit var controller: PlayerContentController
    private val playerContentDelegate by fragmentDelegate<PlayerContentDelegate>()

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
        viewModel.loadPlayerContent()
    }

    private fun setupController() {
        controller = PlayerContentController(object : PlayerContentController.EventHandler {
            override fun onCommentLiked(comment: Comment) {
                Wood.debug("Comment liked")
            }

            override fun onCommentDisliked(comment: Comment) {
                Wood.debug("Comment disliked")
            }

            override fun onCommentExpanded() {
                Wood.debug("Comment expanded")
                viewModel.expandComments()
            }

            override fun onCommentCollapsed() {
                Wood.debug("Comment collapsed")
                viewModel.collapseComments()
            }

            override fun onVideoSelected(video: UIModelVideo) {
                Wood.debug("Video selected")
                playerContentDelegate?.onVideoSelected(video)
                viewModel.loadPlayerContent()
            }

            override fun onVideoLiked(currentVideo: UIModelVideo) {
                Wood.debug("video liked")
            }

            override fun onVideoDisliked(currentVideo: UIModelVideo) {
                Wood.debug("Video disliked")
            }

            override fun onVideoShared(currentVideo: UIModelVideo) {
                Wood.debug("video shared")
            }

            override fun onVideoDownloadRequested(currentVideo: UIModelVideo) {
                Wood.debug("video download requested")
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