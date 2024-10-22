package com.felipeschoffen.onbudget.data.model

data class FinancialAccount(
    val name: String = "",
    val type: AccountType = AccountType.Wallet,
    var balance: String = ""
)

sealed class AccountType(val name: String) {
    data object Wallet: AccountType("Wallet")
    data object Bank : AccountType("Bank")
}