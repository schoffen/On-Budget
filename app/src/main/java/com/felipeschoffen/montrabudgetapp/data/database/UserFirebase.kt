package com.felipeschoffen.montrabudgetapp.data.database

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.auth.RegisterResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.core.error.RegisterError

object UserFirebase : UserDatabase {

    private val _registerResult = mutableStateOf(RegisterResult(false))
    override val registerResult: MutableState<RegisterResult> get() = _registerResult

    private fun getAuth(): FirebaseAuth = Firebase.auth

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError> {
        try {
            val result = getAuth().createUserWithEmailAndPassword(
                registrationInfo.email,
                registrationInfo.password
            ).await()

            result.user?.sendEmailVerification()
            return Result.Success(Unit)
        } catch (e: FirebaseAuthUserCollisionException) {
            return Result.Error(RegisterError.USER_ALREADY_REGISTERED)
        } catch (e: Exception) {
            return Result.Error(RegisterError.UNKNOWN)
        }
    }
}