package com.lutalic.luboard.view.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import com.lutalic.luboard.core.navigator.Navigator
import com.lutalic.luboard.core.uiactions.UiActions
import com.lutalic.luboard.core.views.BaseViewModel
import com.lutalic.luboard.model.accounthelper.AuthInteractionRepository
import com.lutalic.luboard.model.accounthelper.FirebaseAuthRepository
import com.lutalic.luboard.model.accounthelper.user.AccountUser
import com.lutalic.luboard.model.accounthelper.user.FireBaseSimpleUser
import com.lutalic.luboard.view.MainPageFragment

private const val TAG = "LoginRegisterViewModel"

class LoginRegisterViewModel(
    private val navigator: Navigator,
): BaseViewModel() {
    private var authAppRepository: AuthInteractionRepository = FirebaseAuthRepository()
    val userLiveData: LiveData<out AccountUser> = authAppRepository.userLiveData

    fun login(email: String, password: String) {
        Log.d(TAG, "LOGIN!!!")
        authAppRepository.login(email, password)
    }

    fun register(email: String, password: String) {
        Log.d(TAG, "REgISTER!!!")
        authAppRepository.register(email, password)
    }

    fun registrationSuccess(){
        Log.d(TAG, "REG SUCC!!!")
        navigator.launch(MainPageFragment.Screen())
    }

}