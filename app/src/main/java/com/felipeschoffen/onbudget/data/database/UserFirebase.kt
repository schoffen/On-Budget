package com.felipeschoffen.onbudget.data.database

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.felipeschoffen.onbudget.core.RegistrationStep
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.domain.auth.RegisterResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.FirestoreCollections
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.felipeschoffen.onbudget.data.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore

object UserFirebase : UserDatabase {

    private val _registerResult = mutableStateOf(RegisterResult(false))
    override val registerResult: MutableState<RegisterResult> get() = _registerResult

    private fun getAuth(): FirebaseAuth = Firebase.auth
    private fun getDb(): FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError> {
        try {
            val result = getAuth().createUserWithEmailAndPassword(
                registrationInfo.email,
                registrationInfo.password
            ).await()

            result.user?.sendEmailVerification()

            if (result.user != null) {
                getDb().collection(FirestoreCollections.USERS)
                    .document(result.user!!.uid)
                    .set(
                        User(
                            uid = result.user!!.uid,
                            name = registrationInfo.name,
                            email = registrationInfo.email,
                            registrationStep = RegistrationStep.VERIFICATION
                        )
                    )
                    .addOnSuccessListener { Log.d("user_collections", "Successfully added") }
                    .addOnFailureListener { Log.d("user_collections", "Failure to add") }
            }

            return Result.Success(Unit)
        } catch (e: FirebaseAuthUserCollisionException) {
            return Result.Error(RegisterError.USER_ALREADY_REGISTERED)
        } catch (e: Exception) {
            return Result.Error(RegisterError.UNKNOWN)
        }
    }

    override suspend fun loginWithEmail(loginInformation: LoginInformation): Result<Unit, LoginError> {
        try {
            getAuth().signInWithEmailAndPassword(loginInformation.email, loginInformation.password).await()

            return Result.Success(Unit)
        } catch (e: FirebaseAuthInvalidUserException) {
            return Result.Error(LoginError.INVALID_USER)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            return Result.Error(LoginError.INVALID_CREDENTIALS)
        } catch (e: FirebaseException) {
            Log.e("login", e.message.toString())
            return Result.Error(LoginError.UNKNOWN)
        }
    }
}