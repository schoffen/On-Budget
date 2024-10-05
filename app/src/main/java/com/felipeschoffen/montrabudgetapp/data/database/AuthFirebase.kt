package com.felipeschoffen.montrabudgetapp.data.database

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.auth.RegisterResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AuthFirebase : AuthDatabase {

    private val _registerResult = mutableStateOf(RegisterResult(false))
    override val registerResult: MutableState<RegisterResult> get() = _registerResult

    private fun getAuth(): FirebaseAuth = Firebase.auth

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo) {

        getAuth().createUserWithEmailAndPassword(registrationInfo.email, registrationInfo.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _registerResult.value = RegisterResult(true)
                } else {
                    _registerResult.value = RegisterResult(false, task.exception!!.message)
                }
            }
    }
}