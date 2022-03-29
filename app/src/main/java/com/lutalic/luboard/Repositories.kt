package com.lutalic.luboard

import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.model.accounts.FirebaseAccountRepository
import com.lutalic.luboard.model.boards.BoardsRepository
import com.lutalic.luboard.model.boards.RandomBoardsRepository

object Repositories {

    val accountsRepository: AccountsRepository = FirebaseAccountRepository()

    val boardsRepository: BoardsRepository = RandomBoardsRepository()
}