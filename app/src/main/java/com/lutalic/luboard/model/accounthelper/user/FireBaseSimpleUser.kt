package com.lutalic.luboard.model.accounthelper.user

import com.google.firebase.auth.FirebaseUser
import com.lutalic.luboard.model.accounthelper.user.AccountUser

class FireBaseSimpleUser(private val firebaseUser: FirebaseUser) : AccountUser {
    override val email: String?
        get() = firebaseUser.email


}