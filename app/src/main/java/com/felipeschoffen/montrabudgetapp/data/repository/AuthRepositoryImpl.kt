package com.felipeschoffen.montrabudgetapp.data.repository

import com.felipeschoffen.montrabudgetapp.data.database.UserDatabase
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.core.error.LoginError
import com.felipeschoffen.montrabudgetapp.core.error.RegisterError
import com.felipeschoffen.montrabudgetapp.data.model.LoginInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDatabase: UserDatabase
) : AuthRepository {
    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError> {
        return userDatabase.registerWithEmail(registrationInfo)
    }

    override suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError> {
        return userDatabase.loginWithEmail(loginInformation)
    }
}