package com.lutalic.luboard.model

open class AppException(message: String? = null) : RuntimeException(message)

class EmptyFieldException(
    val field: Field
) : AppException()

class PasswordMismatchException : AppException()

class AccountAlreadyExistsException : AppException()

class NoValidateEmailException : AppException()

class AuthException(override val message: String?) : AppException(message)