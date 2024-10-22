package com.felipeschoffen.onbudget.data.repository

import com.felipeschoffen.onbudget.core.RegistrationStep
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.DatabaseError
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.database.UserDatabase
import com.felipeschoffen.onbudget.data.model.FinancialAccount
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.domain.auth.PinHashing
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDatabase: UserDatabase,
    private val pinHashing: PinHashing
) : AuthRepository {
    private var cachedUser: FirebaseUser? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            cachedUser = when (val result = getUserInformation()) {
                is Result.Error -> null
                is Result.Success -> result.data
            }
        }
    }

    override fun getCachedUser(): FirebaseUser? = cachedUser

    override suspend fun getUserInformation(): Result<FirebaseUser?, DatabaseError> {
        when (val result = userDatabase.getUserInformation()) {
            is Result.Error -> {
                cachedUser = null
                return result
            }

            is Result.Success -> {
                cachedUser = result.data
                return result
            }
        }
    }

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError> {
        return userDatabase.registerWithEmail(registrationInfo)
    }

    override suspend fun registerPin(pin: String): Result<Unit, DatabaseError> {
        return when (val result = userDatabase.createPin(PinHashing().hashPin(pin))) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                return updateUserRegistrationStep(RegistrationStep.SETUP_FINANCIAL_ACCOUNT)
            }
        }
    }

    override suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError> {
        return userDatabase.loginWithEmail(loginInformation)
    }

    override suspend fun pinAuthentication(pin: String): Result<Unit, LoginError> {
        val result = getUserInformation()
        if (result is Result.Success) {
            if (pinHashing.hashPin(pin) == result.data?.pin)
                return Result.Success(Unit)
        }

        return Result.Error(LoginError.INVALID_CREDENTIALS)
    }

    override suspend fun updateUserRegistrationStep(registrationStep: RegistrationStep): Result<Unit, DatabaseError> {
        return when (val update = userDatabase.updateUserRegisterStep(registrationStep)) {
            is Result.Error -> Result.Error(update.error)
            is Result.Success -> Result.Success(Unit)
        }
    }

    override suspend fun createFinancialAccount(account: FinancialAccount): Result<Unit, DatabaseError> {
        return userDatabase.createFinancialAccount(
            account = FinancialAccount(
                name = account.name,
                balance = account.balance.ifEmpty { "0" },
                type = account.type
            )
        )
    }
}