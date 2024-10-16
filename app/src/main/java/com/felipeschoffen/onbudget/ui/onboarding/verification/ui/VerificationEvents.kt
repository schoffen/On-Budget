package com.felipeschoffen.onbudget.ui.onboarding.verification.ui

sealed class VerificationEvents {
    data object VerificationSuccessful : VerificationEvents()
    data class ShowMessage(val message: String) : VerificationEvents()
}