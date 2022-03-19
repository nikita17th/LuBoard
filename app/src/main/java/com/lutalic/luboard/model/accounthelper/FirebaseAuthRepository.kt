package com.lutalic.luboard.model.accounthelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.lutalic.luboard.model.accounthelper.user.AccountUser
import com.lutalic.luboard.model.accounthelper.user.FireBaseSimpleUser


class FirebaseAuthRepository() : AuthInteractionRepository {

    private val _firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _userLiveData: MutableLiveData<FireBaseSimpleUser> = MutableLiveData()
    private val _loggedOutLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override val userLiveData: LiveData<FireBaseSimpleUser> = _userLiveData
    override val loggedOutLiveData: LiveData<Boolean> = _loggedOutLiveData

    init {
        if (_firebaseAuth.currentUser != null) {
            _userLiveData.postValue(FireBaseSimpleUser(_firebaseAuth.currentUser!!))
            _loggedOutLiveData.postValue(false)
        }
    }

    override fun login(email: String, password: String) {
        _firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userLiveData.postValue(FireBaseSimpleUser(_firebaseAuth.currentUser!!))
                } else {
                    /*Toast.makeText(
                        application.applicationContext,
                        "Login Failure: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()*/
                }
            }
    }

    override fun register(email: String, password: String) {
        _firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _userLiveData.postValue(FireBaseSimpleUser(_firebaseAuth.currentUser!!))
                } else {
                    /*Toast.makeText(
                        application.applicationContext,
                        "Registration Failure: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()*/
                }
            }
    }

    override fun logOut() {
        _firebaseAuth.signOut()
        _loggedOutLiveData.postValue(true)
    }
}

