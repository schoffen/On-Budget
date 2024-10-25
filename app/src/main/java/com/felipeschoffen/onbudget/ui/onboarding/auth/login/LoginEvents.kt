package com.felipeschoffen.onbudget.ui.onboarding.auth.login

import com.felipeschoffen.onbudget.core.util.RegistrationStep

sealed class LoginEvents {
    data class LoginSuccessful(val registrationStep: RegistrationStep) : LoginEvents()
    data class ShowMessage(val message: String) : LoginEvents()
}