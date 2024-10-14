package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.validations.EmailValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidator: EmailValidator,
    private val errorMessages: ErrorMessages,
) : ViewModel() {

    private val _loginFormState = mutableStateOf(LoginFormState())
    val loginFormState get() = _loginFormState

    private val _loginEvents = Channel<LoginEvents>()
    val loginEvents = _loginEvents.receiveAsFlow()

    fun onEmailChange(newEmail: String) {
        _loginFormState.value = _loginFormState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _loginFormState.value = _loginFormState.value.copy(password = newPassword)
    }

    fun login() {
        validateEmail()
    }

    private fun validateEmail() {
        val isValid: Boolean
        val errorMessage: String?

        when (val result = emailValidator.execute(_loginFormState.value.email)) {
            is Result.Error -> {
                isValid = false
                errorMessage = errorMessages.getErrorMessage(result.error)
            }

            is Result.Success -> {
                isValid = true
                errorMessage = null
            }
        }

        _loginFormState.value = _loginFormState.value.copy(
            isEmailValid = isValid,
            emailErrorMessage = errorMessage
        )
    }
}