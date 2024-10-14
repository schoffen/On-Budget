package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login

sealed class LoginEvents {
    data class LoginSuccessful(val isEmailVerified: Boolean) : LoginEvents()
    data class ShowMessage(val message: String) : LoginEvents()
}