package com.felipeschoffen.montrabudgetapp.ui.onboarding.verification.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.montrabudgetapp.core.error.AuthError
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val errorMessages: ErrorMessages
) : ViewModel() {

    private val _verificationEvents = Channel<VerificationEvents>()
    val verificationEvents = _verificationEvents.receiveAsFlow()

    fun onContinueClicked() {
        viewModelScope.launch {
            val currentUser = authRepository.getCurrentUser()

            if (currentUser != null) {
                if (!currentUser.isEmailVerified)
                    _verificationEvents.send(VerificationEvents.ShowMessage(errorMessages.getErrorMessage(AuthError.EMAIL_NOT_VERIFIED)))
                else
                    _verificationEvents.send(VerificationEvents.VerificationSuccessful)
            }

            _verificationEvents.send(VerificationEvents.ShowMessage(errorMessages.getErrorMessage(AuthError.EMAIL_NOT_VERIFIED)))
        }
    }
}