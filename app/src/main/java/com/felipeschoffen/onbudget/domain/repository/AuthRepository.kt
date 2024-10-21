package com.felipeschoffen.onbudget.domain.repository

import com.felipeschoffen.onbudget.core.RegistrationStep
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.DatabaseError
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.felipeschoffen.onbudget.data.model.LoginInformation

interface AuthRepository {
    fun getCachedUser(): FirebaseUser?
    suspend fun updateUserRegistrationStep(registrationStep: RegistrationStep): Result<Unit, DatabaseError>
    suspend fun getUserInformation(): Result<FirebaseUser?, DatabaseError>
    suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError>
    suspend fun registerPin(pin: String): Result<Unit, DatabaseError>
    suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError>
    suspend fun pinAuthentication(pin: String): Result<Unit, LoginError>
}