package com.felipeschoffen.montrabudgetapp.domain.repository

import com.felipeschoffen.montrabudgetapp.core.error.RegisterError
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.core.Result

interface AuthRepository {

    suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError>
}