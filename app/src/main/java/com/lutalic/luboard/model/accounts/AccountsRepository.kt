package com.lutalic.luboard.model.accounts

import kotlinx.coroutines.flow.Flow
import com.lutalic.luboard.model.AccountAlreadyExistsException
import com.lutalic.luboard.model.AuthException
import com.lutalic.luboard.model.EmptyFieldException
import com.lutalic.luboard.model.PasswordMismatchException
import com.lutalic.luboard.model.accounts.entities.Account
import com.lutalic.luboard.model.accounts.entities.SignUpData

/**
 * Repository with account-related actions, e.g. sign-in, sign-up, edit account etc.
 */
interface AccountsRepository {

    /**
     * Whether user is signed-in or not.
     */
    suspend fun isSignedIn(): Boolean

    /**
     * Try to sign-in with the email and password.
     * @throws [EmptyFieldException], [AuthException]
     */
    suspend fun signIn(email: String, password: String)

    /**
     * Create a new account.
     * @throws [EmptyFieldException], [PasswordMismatchException], [AccountAlreadyExistsException]
     */
    suspend fun signUp(signUpData: SignUpData)

    /**
     * Sign-out from the app.
     */
    fun logout()

    /**
     * Get the account info of the current signed-in user.
     */
    fun getAccount(): Flow<Account?>

}