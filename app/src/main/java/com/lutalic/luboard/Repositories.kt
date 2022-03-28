package com.lutalic.luboard

import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.model.accounts.FirebaseAccountRepository

object Repositories {

    val accountsRepository: AccountsRepository = FirebaseAccountRepository()
}