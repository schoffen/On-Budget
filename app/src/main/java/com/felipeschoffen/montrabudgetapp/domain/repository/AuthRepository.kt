package com.felipeschoffen.montrabudgetapp.domain.repository

import com.felipeschoffen.montrabudgetapp.core.error.RegisterError
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.core.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError>
    fun getCurrentUser(): FirebaseUser?
}