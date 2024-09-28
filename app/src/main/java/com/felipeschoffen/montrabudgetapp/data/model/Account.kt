package com.felipeschoffen.montrabudgetapp.data.model

class Account(
    val name: String,
    val type: AccountType,
    private var balance: Float
) {
    fun getBalance() = balance

    fun increaseBalance(amount: Float) {
        balance += amount
    }

    fun decreaseBalance(amount: Float) {
        balance -= amount
    }
}

sealed class AccountType() {
    data object Wallet: AccountType() {
        val typeName = "Wallet"
    }
    data object Bank : AccountType() {
        val typeName = "Bank"
    }
}