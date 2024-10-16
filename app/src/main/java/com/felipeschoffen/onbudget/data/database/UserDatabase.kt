package com.felipeschoffen.onbudget.data.database

import androidx.compose.runtime.MutableState
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.domain.auth.RegisterResult
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.model.LoginInformation

interface UserDatabase {
    val registerResult: MutableState<RegisterResult>
    suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError>
    suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError>
}