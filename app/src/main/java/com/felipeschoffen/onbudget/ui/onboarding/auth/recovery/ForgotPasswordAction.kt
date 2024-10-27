package com.felipeschoffen.onbudget.ui.onboarding.auth.recovery

sealed interface ForgotPasswordAction {
    data object Continue : ForgotPasswordAction
    data class OnEmailChange(val value: String) : ForgotPasswordAction
}