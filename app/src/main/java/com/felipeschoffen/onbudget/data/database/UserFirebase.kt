package com.felipeschoffen.onbudget.data.database

import android.util.Log
import com.felipeschoffen.onbudget.core.RegistrationStep
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.DatabaseError
import com.felipeschoffen.onbudget.core.error.LoginError
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.FirestoreCollections
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.ktx.firestore

object UserFirebase : UserDatabase {
    private val auth: FirebaseAuth = Firebase.auth

    override suspend fun registerWithEmail(registrationInfo: RegistrationInfo): Result<Unit, RegisterError> {
        try {
            val result = auth.createUserWithEmailAndPassword(
                registrationInfo.email,
                registrationInfo.password
            ).await()

            result.user?.sendEmailVerification()

            if (result.user != null) {
                Firebase.firestore.collection(FirestoreCollections.USERS)
                    .document(result.user!!.uid)
                    .set(
                        FirebaseUser(
                            uid = result.user!!.uid,
                            name = registrationInfo.name,
                            email = registrationInfo.email,
                            registrationStep = RegistrationStep.VERIFICATION
                        )
                    )
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
            auth.signInWithEmailAndPassword(loginInformation.email, loginInformation.password).await()

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

    override suspend fun getUserInformation(): Result<FirebaseUser?, DatabaseError> {
        return try {
            val document = Firebase.firestore.collection(FirestoreCollections.USERS)
                .document(auth.currentUser!!.uid)
                .get()
                .await()

            if (document.exists())
                Result.Success(document.toObject(FirebaseUser::class.java))
            else
                Result.Error(DatabaseError.DOCUMENT_NOT_FOUND)
        } catch (e: Exception) {
            Log.e("firebase", e.message.toString())
            Result.Error(DatabaseError.UNKNOWN)
        }
    }
}