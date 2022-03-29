package com.lutalic.luboard.presentation.main.auth.signup

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lutalic.luboard.core.uiactions.UiActions
import kotlinx.coroutines.launch
import com.lutalic.luboard.R
import com.lutalic.luboard.model.*
import com.lutalic.luboard.model.accounts.AccountsRepository
import com.lutalic.luboard.model.accounts.entities.SignUpData
import com.lutalic.luboard.utils.MutableUnitLiveEvent
import com.lutalic.luboard.utils.publishEvent
import com.lutalic.luboard.utils.requireValue
import com.lutalic.luboard.utils.share

class SignUpViewModel(
    private val accountsRepository: AccountsRepository,
    private val uiActions: UiActions
) : ViewModel() {


    private val _goBackEvent = MutableUnitLiveEvent()
    val goBackEvent = _goBackEvent.share()

    private val _state = MutableLiveData(State())
    val state = _state.share()

    fun signUp(signUpData: SignUpData) {
        viewModelScope.launch {
            showProgress()
            try {
                accountsRepository.signUp(signUpData)
                uiActions.toast("The account has been created, you can login after you confirm the email")
                goBack()
            } catch (e: EmptyFieldException) {
                processEmptyFieldException(e)
            } catch (e: PasswordMismatchException) {
                processPasswordMismatchException()
            } catch (e: AccountAlreadyExistsException) {
                processAccountAlreadyExistsException()
            } catch (e: AuthException) {
                uiActions.toast(e.message ?: "Unknown registration error :C")
            } finally {
                hideProgress()
            }
        }
    }

    private fun processEmptyFieldException(e: EmptyFieldException) {
        _state.value = when (e.field) {
            Field.Email -> _state.requireValue()
                .copy(emailErrorMessageRes = R.string.field_is_empty)
            Field.Password -> _state.requireValue()
                .copy(passwordErrorMessageRes = R.string.field_is_empty)
            else -> throw IllegalStateException("Unknown field")
        }
    }

    private fun processPasswordMismatchException() {
        _state.value = _state.requireValue()
            .copy(repeatPasswordErrorMessageRes = R.string.password_mismatch)
    }

    private fun processAccountAlreadyExistsException() {
        _state.value = _state.requireValue()
            .copy(emailErrorMessageRes = R.string.account_already_exists)
    }

    private fun showProgress() {
        _state.value = State(signUpInProgress = true)
    }

    private fun hideProgress() {
        _state.value = _state.requireValue().copy(signUpInProgress = false)
    }


    private fun goBack() = _goBackEvent.publishEvent()

    data class State(
        @StringRes val emailErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val passwordErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val repeatPasswordErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val usernameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        val signUpInProgress: Boolean = false,
    ) {
        val showProgress: Boolean get() = signUpInProgress
        val enableViews: Boolean get() = !signUpInProgress
    }

    companion object {
        const val NO_ERROR_MESSAGE = 0
    }

}