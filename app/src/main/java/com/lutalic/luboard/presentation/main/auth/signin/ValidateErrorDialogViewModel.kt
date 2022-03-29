package com.lutalic.luboard.presentation.main.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lutalic.luboard.core.uiactions.UiActions
import com.lutalic.luboard.model.accounts.AccountsRepository
import kotlinx.coroutines.launch

class ValidateErrorDialogViewModel(
    private val accountsRepository: AccountsRepository,
    private val uiActions: UiActions
) : ViewModel() {

    fun sendValidateEmail() = viewModelScope.launch {
        accountsRepository.sendValidateEmail()
        uiActions.toast("Your email is not confirmed please check your inbox")
    }
}