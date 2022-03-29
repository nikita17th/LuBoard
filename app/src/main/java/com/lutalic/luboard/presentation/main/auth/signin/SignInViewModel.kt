package com.lutalic.luboard.presentation.main.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lutalic.luboard.core.uiactions.UiActions
import kotlinx.coroutines.launch
import com.lutalic.luboard.model.AuthException
import com.lutalic.luboard.model.EmptyFieldException
import com.lutalic.luboard.model.Field
import com.lutalic.luboard.model.NoValidateEmailException
import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.utils.MutableUnitLiveEvent
import com.lutalic.luboard.utils.publishEvent
import com.lutalic.luboard.utils.requireValue
import com.lutalic.luboard.utils.share
import java.lang.Exception

class SignInViewModel(
    private val accountsRepository: AccountsRepository,
    private val uiActions: UiActions
) : ViewModel() {

    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _clearPasswordEvent = MutableUnitLiveEvent()
    val clearPasswordEvent = _clearPasswordEvent.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    private val _validateEmailEvent = MutableUnitLiveEvent()
    val validateEmailEvent = _validateEmailEvent.share()


    fun signIn(email: String, password: String) = viewModelScope.launch {
        showProgress()
        try {
            accountsRepository.signIn(email, password)
            launchTabsScreen()
        } catch (e: EmptyFieldException) {
            processEmptyFieldException(e)
        } catch (e: NoValidateEmailException) {
            processNoValidateEmail()
        } catch (e: AuthException) {
            processAuthException(e)
        }
    }

    private fun processNoValidateEmail() {
        _validateEmailEvent.publishEvent()
        _state.value = _state.requireValue().copy(
            signInInProgress = false
        )
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = _state.requireValue().copy(
            emptyEmailError = e.field == Field.Email,
            emptyPasswordError = e.field == Field.Password,
            signInInProgress = false
        )
    }

    private fun processAuthException(e: Exception) {
        _state.value = _state.requireValue().copy(
            signInInProgress = false
        )
        clearPasswordField()
        uiActions.toast(e.message ?: "Incorrect email or password")
    }

    private fun showProgress() {
        _state.value = State(signInInProgress = true)
    }

    private fun clearPasswordField() = _clearPasswordEvent.publishEvent()


    private fun launchTabsScreen() = _navigateToTabsEvent.publishEvent()

    data class State(
        val emptyEmailError: Boolean = false,
        val emptyPasswordError: Boolean = false,
        val signInInProgress: Boolean = false
    ) {
        val showProgress: Boolean get() = signInInProgress
        val enableViews: Boolean get() = !signInInProgress
    }
}