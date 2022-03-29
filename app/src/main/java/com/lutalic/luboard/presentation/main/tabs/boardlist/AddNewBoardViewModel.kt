package com.lutalic.luboard.presentation.main.tabs.boardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lutalic.luboard.model.boards.BoardsRepository
import com.lutalic.luboard.model.boards.entities.Board
import kotlinx.coroutines.launch

class AddNewBoardViewModel(
    private val boardsRepository: BoardsRepository
) : ViewModel() {

    fun addNewBoard(board: Board) = viewModelScope.launch {
        boardsRepository.newBoard(board)
    }
}