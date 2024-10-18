package com.felipeschoffen.onbudget.data.model

import com.felipeschoffen.onbudget.core.RegistrationStep

data class User(
    val uid: String,
    val name: String,
    val email: String,
    val pin: String? = null,
    val registrationStep: RegistrationStep,
    val financialAccounts: List<FinancialAccount>? = null
)
