package com.lutalic.luboard.presentation.main.tabs.boardlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lutalic.luboard.R
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.databinding.FragmentBoardListBinding
import com.lutalic.luboard.model.boards.entities.Board
import com.lutalic.luboard.presentation.main.tabs.boardlist.adapters.BoardsAdapter
import com.lutalic.luboard.utils.viewModelCreator

class BoardListFragment : Fragment(R.layout.fragment_board_list) {

    private val viewModel by viewModelCreator {
        BoardListViewModel(Repositories.boardsRepository)
    }

    private lateinit var binding: FragmentBoardListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoardListBinding.bind(view)
        initRecyclerView()
        binding.floatingButton.setOnClickListener {
            onAddClick()
        }
        hideButtonOnScroll()

    }

    private fun hideButtonOnScroll() {
        binding.boardsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.floatingButton.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && binding.floatingButton.isShown) {
                    binding.floatingButton.hide()
                }
            }
        })
    }

    private fun initRecyclerView() {
        val boardsAdapter = BoardsAdapter(object : BoardsAdapter.Listener {
            override fun onBoardClick(board: Board) {
                findNavController().navigate(
                    BoardListFragmentDirections.actionDashboardFragmentToBoxFragment(
                        board.id
                    )
                )
            }
        })
        binding.boardsRecyclerView.adapter = boardsAdapter
        binding.boardsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        boardsAdapter.updateAdapter(viewModel.boards.value ?: mutableListOf())
        viewModel.boards.observe(viewLifecycleOwner) {
            boardsAdapter.updateAdapter(it)
        }
    }

    private fun onAddClick() {
        findNavController().navigate(
            BoardListFragmentDirections.actionListBoardFragmentToAddNewBoardFragment()
        )
    }

}