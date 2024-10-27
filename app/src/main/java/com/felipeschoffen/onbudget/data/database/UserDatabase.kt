package com.felipeschoffen.onbudget.data.database

import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.core.util.Result
import com.felipeschoffen.onbudget.core.util.errors.DatabaseError
import com.felipeschoffen.onbudget.core.util.errors.LoginError
import com.felipeschoffen.onbudget.core.util.errors.RegisterError
import com.felipeschoffen.onbudget.data.model.FinancialAccount
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.felipeschoffen.onbudget.data.model.FirebaseUser

interface UserDatabase {
    suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError>
    suspend fun createPin(pin: String): Result<Unit, DatabaseError>
    suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError>
    suspend fun getUserInformation(): Result<FirebaseUser?, DatabaseError>
    suspend fun updateUserRegisterStep(registrationStep: RegistrationStep): Result<Unit, DatabaseError>
    suspend fun createFinancialAccount(account: FinancialAccount): Result<Unit, DatabaseError>
    suspend fun sendResetPasswordEmail(email: String): Result<Unit, DatabaseError>
    suspend fun signOut()
}