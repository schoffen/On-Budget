package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

sealed class RegisterEvents {
    data object RegisterSuccessful : RegisterEvents()
    data class ShowError(val errorMessage: String) : RegisterEvents()
}