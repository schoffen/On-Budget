package com.felipeschoffen.onbudget.data.model

data class FinancialAccount(
    val name: String,
    val type: AccountType,
    private var balance: Float
)

sealed class AccountType(val name: String) {
    data object Wallet: AccountType("Wallet")
    data object Bank : AccountType("Bank")
}