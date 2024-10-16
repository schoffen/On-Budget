package com.felipeschoffen.onbudget.ui.onboarding.auth.register

sealed class RegisterEvents {
    data object RegisterSuccessful : RegisterEvents()
    data class ShowMessage(val message: String) : RegisterEvents()
}