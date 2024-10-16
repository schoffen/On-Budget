package com.felipeschoffen.onbudget.domain.auth

import android.service.autofill.UserData

data class RegisterResult(
    var isSuccessful: Boolean,
    var errorMessage: String? = null,
    var userData: UserData? = null
)