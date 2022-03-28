package com.lutalic.luboard.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.utils.MutableLiveEvent
import com.lutalic.luboard.utils.publishEvent
import com.lutalic.luboard.utils.share
import kotlinx.coroutines.delay

/**
 * SplashViewModel checks whether user is signed-in or not.
 */
class SplashViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _launchMainScreenEvent = MutableLiveEvent<Boolean>()
    val launchMainScreenEvent = _launchMainScreenEvent.share()

    init {
        viewModelScope.launch {
            delay(2000) // FIXME типа грузим бд и сплешкрин крутит
            _launchMainScreenEvent.publishEvent(accountsRepository.isSignedIn())
        }
    }
}