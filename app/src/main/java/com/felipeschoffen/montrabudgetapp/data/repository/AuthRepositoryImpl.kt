package com.felipeschoffen.montrabudgetapp.data.repository

import androidx.compose.runtime.MutableState
import com.felipeschoffen.montrabudgetapp.data.database.AuthDatabase
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.auth.RegisterResult
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDatabase: AuthDatabase
) : AuthRepository {

    override val registerResult: MutableState<RegisterResult>
        get() = authDatabase.registerResult

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo) {
        authDatabase.registerWithEmail(registrationInfo)
    }

}