package com.felipeschoffen.onbudget.data.repository

import com.felipeschoffen.onbudget.data.database.UserDatabase
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.DatabaseError
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.felipeschoffen.onbudget.data.model.LoginInformation
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDatabase: UserDatabase
) : AuthRepository {

    override suspend fun getUserInformation(): Result<FirebaseUser, DatabaseError> {
        return when(val result = userDatabase.getUserInformation()) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                if (result.data != null)
                    return Result.Success(result.data)
                else
                    return Result.Error(DatabaseError.DOCUMENT_NOT_FOUND)
            }
        }
    }

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError> {
        return userDatabase.registerWithEmail(registrationInfo)
    }

    override suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError> {
        return userDatabase.loginWithEmail(loginInformation)
    }
}