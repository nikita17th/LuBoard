package com.lutalic.luboard.presentation.main.tabs.boardlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lutalic.luboard.R
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.databinding.FragmentBoardBinding
import com.lutalic.luboard.utils.observeEvent
import com.lutalic.luboard.utils.viewModelCreator

class BoardFragment : Fragment(R.layout.fragment_board) {

    private lateinit var binding: FragmentBoardBinding

    private val viewModel by viewModelCreator {
        BoardViewModel()
    }

    private val args by navArgs<BoardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoardBinding.bind(view)
        binding.boardTextView.text = "${resources.getString(R.string.this_is_board)} ${args.boardId}"
        binding.goBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}