package com.felipeschoffen.onbudget.domain.repository

import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun getCurrentUser(): FirebaseUser?
    suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError>
    suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError>
}