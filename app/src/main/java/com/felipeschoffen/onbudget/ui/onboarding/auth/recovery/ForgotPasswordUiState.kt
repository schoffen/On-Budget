package com.felipeschoffen.onbudget.ui.onboarding.auth.recovery

data class ForgotPasswordUiState(
    val email: String = "",
    val isEmailValid: Boolean = true,
    val emailErrorMessage: String? = null,
    val isLoading: Boolean = false
)