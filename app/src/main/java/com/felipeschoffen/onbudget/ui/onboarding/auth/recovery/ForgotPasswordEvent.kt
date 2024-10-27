package com.felipeschoffen.onbudget.ui.onboarding.auth.recovery

sealed interface ForgotPasswordEvent {
    data object Continue : ForgotPasswordEvent
    data class ShowError(val message: String) : ForgotPasswordEvent
}