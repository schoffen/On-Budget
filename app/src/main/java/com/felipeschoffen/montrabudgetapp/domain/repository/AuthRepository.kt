package com.felipeschoffen.montrabudgetapp.domain.repository

import androidx.compose.runtime.MutableState
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.auth.RegisterResult

interface AuthRepository {

    val registerResult: MutableState<RegisterResult>
    suspend fun registerWithEmail(registrationInfo: RegistrationInfo)
}