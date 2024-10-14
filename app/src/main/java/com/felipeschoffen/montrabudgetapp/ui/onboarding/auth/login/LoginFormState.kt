package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val emailErrorMessage: String? = null
)