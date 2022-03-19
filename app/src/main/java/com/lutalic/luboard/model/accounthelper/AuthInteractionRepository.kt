package com.lutalic.luboard.model.accounthelper

import androidx.lifecycle.LiveData
import com.lutalic.luboard.core.model.Repository
import com.lutalic.luboard.model.accounthelper.user.AccountUser

interface AuthInteractionRepository : Repository {

    val userLiveData: LiveData<out AccountUser>
    val loggedOutLiveData: LiveData<Boolean>

    fun login(email: String, password: String)
    fun register(email: String, password: String)
    fun logOut()
}