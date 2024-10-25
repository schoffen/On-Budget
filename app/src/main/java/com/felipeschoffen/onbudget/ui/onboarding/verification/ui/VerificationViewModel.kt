package com.felipeschoffen.onbudget.ui.onboarding.verification.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.core.util.errors.AuthError
import com.felipeschoffen.onbudget.core.util.errors.toString
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _events = Channel<VerificationEvents>()
    val events = _events.receiveAsFlow()

    private val _userEmail = Firebase.auth.currentUser?.email
    val userEmail = _userEmail

    fun onContinueClicked() {
        viewModelScope.launch {
            Firebase.auth.currentUser?.reload()?.await()

            if (Firebase.auth.currentUser != null) {
                if (!Firebase.auth.currentUser?.isEmailVerified!!)
                    _events.send(
                        VerificationEvents.ShowMessage(AuthError.EMAIL_NOT_VERIFIED.toString(resourceProvider))
                    )
                else {
                    authRepository.updateUserRegistrationStep(RegistrationStep.SETUP_PIN)
                    _events.send(VerificationEvents.VerificationSuccessful)
                }
            }
        }
    }

    fun resendEmailVerification() {
        Firebase.auth.currentUser?.sendEmailVerification()
    }

    fun signOut() = viewModelScope.launch { authRepository.signOut() }
}