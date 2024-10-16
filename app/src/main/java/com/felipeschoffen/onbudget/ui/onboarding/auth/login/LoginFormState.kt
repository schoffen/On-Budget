package com.felipeschoffen.onbudget.ui.onboarding.auth.login

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null
)