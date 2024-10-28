package com.felipeschoffen.onbudget.ui.onboarding.auth.recovery

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.util.errors.toString
import com.felipeschoffen.onbudget.core.util.onError
import com.felipeschoffen.onbudget.core.util.onSuccess
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
import com.felipeschoffen.onbudget.domain.validations.EmailValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailValidator: EmailValidator,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _uiState = mutableStateOf(ForgotPasswordUiState())
    val uiState get() = _uiState

    private val _uiEvent = Channel<ForgotPasswordEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: ForgotPasswordAction) {
        when (action) {
            is ForgotPasswordAction.Continue -> onContinueClicked()
            is ForgotPasswordAction.OnEmailChange -> onEmailChange(action.value)
        }
    }

    private fun onContinueClicked() {
        if (_uiState.value.isEmailValid) {
            changeLoading(true)
            viewModelScope.launch {
                authRepository.sendResetPasswordEmail(_uiState.value.email)
                    .onSuccess {
                        _uiEvent.send(ForgotPasswordEvent.Continue)
                    }
                    .onError { error ->
                        _uiEvent.send(ForgotPasswordEvent.ShowError(error.toString(resourceProvider)))
                    }
                changeLoading(false)
            }
        }
    }

    private fun validateEmail() {
        emailValidator.execute(_uiState.value.email)
            .onSuccess {
                _uiState.value = _uiState.value.copy(isEmailValid = true, emailErrorMessage = null)
            }
            .onError { error ->
                _uiState.value = _uiState.value.copy(
                    isEmailValid = false,
                    emailErrorMessage = error.toString(resourceProvider)
                )
            }
    }

    private fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
        validateEmail()
    }

    private fun changeLoading(value: Boolean) = _uiState.value.copy(isLoading = value)
}