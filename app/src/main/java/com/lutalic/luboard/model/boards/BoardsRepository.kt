package com.lutalic.luboard.model.boards

import com.lutalic.luboard.model.boards.entities.Board
import kotlinx.coroutines.flow.Flow

interface BoardsRepository {

    /**
     * read boards list after change in bd
     */
    suspend fun readChangedData()

    /**
     * delete the current board
     */
    suspend fun deleteBoard(board: Board)


    /**
     * replace the board (edit)
     */
    suspend fun rewriteBoard(from: Board, to: Board)

    /**
     * get flow of listBoards
     */
    fun getBoards(): Flow<List<Board>>


    suspend fun loadPostsForBoard(board: Board)

    /**
     * add new board
     */
    suspend fun newBoard(board: Board)

}