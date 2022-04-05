package com.lutalic.luboard.model.boards

import com.lutalic.luboard.model.boards.entities.Board
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

class RandomBoardsRepository : BoardsRepository { // FIXME TO realtime db

    private var currentListFlow = MutableStateFlow<MutableList<Board>>(mutableListOf())

    init{
        runBlocking {
            withContext(Dispatchers.Default) {
                readChangedData()
            }
        }
    }

    override suspend fun readChangedData() = withContext(Dispatchers.Default) {
        val boards = mutableListOf<Board>()
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        for (i in 1..50) {
            boards.add(
                Board(
                    name = (1..10).map { _ -> Random.nextInt(0, charPool.size) }
                        .map(charPool::get)
                        .joinToString(""),
                    id = i,
                    countUsers = Random.nextInt(0, 20),
                )
            )
        }
        currentListFlow.value = boards.toMutableList()
    }

    override suspend fun deleteBoard(board: Board) {
        throw UnsupportedOperationException()
    }

    override suspend fun setUserPriority(priority: Int, board: Board) {
        throw UnsupportedOperationException()
    }

    override suspend fun rewriteBoard(from: Board, to: Board) {
        throw UnsupportedOperationException()
    }

    override fun getBoards(): Flow<List<Board>> = currentListFlow

    override suspend fun newBoard(board: Board) {
        val mutableListOf = mutableListOf<Board>()
        mutableListOf.addAll(currentListFlow.value)
        mutableListOf.add(board)
        currentListFlow.value = mutableListOf
    }
}