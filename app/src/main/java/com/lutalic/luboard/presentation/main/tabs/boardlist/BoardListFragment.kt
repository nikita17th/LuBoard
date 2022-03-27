package com.lutalic.luboard.presentation.main.tabs.boardlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lutalic.luboard.R
import com.lutalic.luboard.databinding.FragmentBoardListBinding
import com.lutalic.luboard.utils.viewModelCreator

class BoardListFragment : Fragment(R.layout.fragment_board_list) {

    private val viewModel by viewModelCreator { BoardListViewModel() }

    private lateinit var binding: FragmentBoardListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoardListBinding.bind(view)
        binding.openBoardButton1.setOnClickListener {
            findNavController().navigate(BoardListFragmentDirections.actionDashboardFragmentToBoxFragment(1))
        }
        binding.openBoardButton2.setOnClickListener {
            findNavController().navigate(BoardListFragmentDirections.actionDashboardFragmentToBoxFragment(2))
        }
        binding.openBoardButton3.setOnClickListener {
            findNavController().navigate(BoardListFragmentDirections.actionDashboardFragmentToBoxFragment(3))
        }
    }
}