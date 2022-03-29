package com.lutalic.luboard.model.accounts

import com.google.firebase.auth.FirebaseAuth
import com.lutalic.luboard.model.AuthException
import com.lutalic.luboard.model.EmptyFieldException
import com.lutalic.luboard.model.Field
import com.lutalic.luboard.model.NoValidateEmailException
import com.lutalic.luboard.model.accounts.entities.Account
import com.lutalic.luboard.model.accounts.entities.SignUpData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class FirebaseAccountRepository : AccountsRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentAccountFlow = MutableStateFlow<Account?>(null)

    init {
        if (firebaseAuth.currentUser != null && firebaseAuth.currentUser?.isEmailVerified == true) {
            currentAccountFlow.value = Account(firebaseAuth.currentUser?.email!!)
        }
    }

    override fun isSignedIn(): Boolean {
        return currentAccountFlow.value != null
    }

    override suspend fun signIn(email: String, password: String) {
        withContext(Dispatchers.IO) {
            if (email.isBlank()) {
                throw EmptyFieldException(Field.Email)
            }
            if (password.isBlank()) {
                throw EmptyFieldException(Field.Password)
            }
            try {
                val singInResult =
                    firebaseAuth.signInWithEmailAndPassword(email, password).await()
                if (singInResult.user?.isEmailVerified == false) {
                    throw NoValidateEmailException()
                }
                currentAccountFlow.value = Account(singInResult.user?.email!!)
            } catch (e: NoValidateEmailException) {
                throw NoValidateEmailException()
            } catch (e: Exception) {
                throw AuthException(e.message)
            }
        }
    }

    override suspend fun signUp(signUpData: SignUpData) {
        withContext(Dispatchers.IO) {
            signUpData.validate()
            try {
                firebaseAuth.createUserWithEmailAndPassword(
                    signUpData.email,
                    signUpData.password
                ).await()
            } catch (e: Exception) {
                throw AuthException(e.message)
            }
            sendValidateEmail()
        }
    }

    override suspend fun sendValidateEmail() {
        firebaseAuth.currentUser?.sendEmailVerification()
    }

    override fun logout() {
        firebaseAuth.signOut()
        currentAccountFlow.value = null
    }

    override fun getAccount(): Flow<Account?> = currentAccountFlow

}
