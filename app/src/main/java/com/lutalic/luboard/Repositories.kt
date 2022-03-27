package com.lutalic.luboard

import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.model.accounts.InMemoryAccountsRepository

object Repositories {

    val accountsRepository: AccountsRepository = InMemoryAccountsRepository()


}