package com.felipeschoffen.montrabudgetapp.ui.onboarding.verification.ui

sealed class VerificationEvents {
    data object VerificationSuccessful : VerificationEvents()
    data class ShowMessage(val message: String) : VerificationEvents()
}