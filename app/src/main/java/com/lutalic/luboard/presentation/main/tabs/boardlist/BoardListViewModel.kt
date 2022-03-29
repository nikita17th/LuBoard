package com.lutalic.luboard.presentation.main.tabs.boardlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lutalic.luboard.model.boards.BoardsRepository
import com.lutalic.luboard.model.boards.entities.Board
import com.lutalic.luboard.utils.share
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BoardListViewModel(
    private val boardsRepository: BoardsRepository
) : ViewModel() {
    private val _boards = MutableLiveData<List<Board>>()
    val boards = _boards.share()

    init {
        viewModelScope.launch {
            boardsRepository.getBoards().collect {
                _boards.value = it
            }
        }
    }

}