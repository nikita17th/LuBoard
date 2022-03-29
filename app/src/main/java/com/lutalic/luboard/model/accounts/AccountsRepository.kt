package com.lutalic.luboard.model.accounts

import com.lutalic.luboard.model.accounts.entities.Account
import com.lutalic.luboard.model.accounts.entities.SignUpData
import kotlinx.coroutines.flow.Flow

/**
 * Repository with account-related actions, e.g. sign-in, sign-up, edit account etc.
 */
interface AccountsRepository {

    /**
     * Whether user is signed-in or not.
     */
    fun isSignedIn(): Boolean

    /**
     * Try to sign-in with the email and password.
     */
    suspend fun signIn(email: String, password: String)

    /**
     * Create a new account.
     */
    suspend fun signUp(signUpData: SignUpData)

    /**
     * Sends a confirmation email
     */
    suspend fun sendValidateEmail()

    /**
     * Sign-out from the app.
     */
    fun logout()

    /**
     * Get the account info of the current signed-in user.
     */
    fun getAccount(): Flow<Account?>

}