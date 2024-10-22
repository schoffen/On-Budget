package com.felipeschoffen.onbudget.ui.financial_account

sealed class CreateAccountEvents {
    data object CreateSuccessful : CreateAccountEvents()
    data class ShowMessage(val message: String) : CreateAccountEvents()
}