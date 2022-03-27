package com.lutalic.luboard.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.utils.share

class MainActivityViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email = _email.share()

    init {
        viewModelScope.launch {
            // listening for the current account and send the username to be displayed in the toolbar
            accountsRepository.getAccount().collect {
                if (it == null) {
                    _email.value = ""
                } else {
                    _email.value = it.email
                }
            }
        }
    }
}