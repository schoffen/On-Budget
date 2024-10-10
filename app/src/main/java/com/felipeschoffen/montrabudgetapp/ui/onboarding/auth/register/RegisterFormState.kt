package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val nameErrorMessage: String? = null,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null,
    val isTermsChecked: Boolean = false
)