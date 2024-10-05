package com.felipeschoffen.montrabudgetapp.data.database

import androidx.compose.runtime.MutableState
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.auth.RegisterResult

interface AuthDatabase {
    val registerResult: MutableState<RegisterResult>
    suspend fun registerWithEmail(registrationInfo: RegistrationInfo)
}