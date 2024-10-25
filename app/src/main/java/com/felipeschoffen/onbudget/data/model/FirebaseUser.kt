package com.felipeschoffen.onbudget.data.model

import com.felipeschoffen.onbudget.core.util.RegistrationStep

data class FirebaseUser(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val pin: String? = null,
    val registrationStep: RegistrationStep? = null
)
