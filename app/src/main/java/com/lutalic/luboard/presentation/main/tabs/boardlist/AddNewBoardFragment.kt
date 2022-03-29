package com.lutalic.luboard.presentation.main.tabs.boardlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lutalic.luboard.R
import com.lutalic.luboard.Repositories
import com.lutalic.luboard.databinding.FagmentAddNewBoardBinding
import com.lutalic.luboard.databinding.FragmentBoardBinding
import com.lutalic.luboard.model.boards.entities.Board
import com.lutalic.luboard.utils.viewModelCreator
import kotlin.random.Random

class AddNewBoardFragment : Fragment(R.layout.fagment_add_new_board) {

    private lateinit var binding: FagmentAddNewBoardBinding

    private val viewModel by viewModelCreator {
        AddNewBoardViewModel(Repositories.boardsRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FagmentAddNewBoardBinding.bind(view)
        createButtonProcessing()
    }

    private fun createButtonProcessing() {
        binding.createNewBoardBtn.setOnClickListener {
            viewModel.addNewBoard(
                Board( // FIXME
                    name = binding.nameOfBoardInput.text.toString(),
                    id = -1,
                    Random.nextInt(0, 20),
                    1
                )
            )
            findNavController().popBackStack()
        }
    }

}