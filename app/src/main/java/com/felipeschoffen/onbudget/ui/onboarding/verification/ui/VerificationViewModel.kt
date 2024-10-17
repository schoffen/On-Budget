package com.felipeschoffen.onbudget.ui.onboarding.verification.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.error.AuthError
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ErrorMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val errorMessages: ErrorMessages
) : ViewModel() {

    private val _verificationEvents = Channel<VerificationEvents>()
    val verificationEvents = _verificationEvents.receiveAsFlow()

    private val _userEmail = authRepository.getCurrentUser()?.email
    val userEmail = _userEmail

    fun onContinueClicked() {
        viewModelScope.launch {
            authRepository.getCurrentUser()?.reload()?.await()

            val currentUser = authRepository.getCurrentUser()

            if (currentUser != null) {
                if (!currentUser.isEmailVerified)
                    _verificationEvents.send(VerificationEvents.ShowMessage(errorMessages.getErrorMessage(AuthError.EMAIL_NOT_VERIFIED)))
                else
                    _verificationEvents.send(VerificationEvents.VerificationSuccessful)
            }
        }
    }

    fun resendEmailVerification() {
        authRepository.getCurrentUser()?.sendEmailVerification()
    }
}