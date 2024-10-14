package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.data.model.LoginInformation
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.validations.EmailValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidator: EmailValidator,
    private val errorMessages: ErrorMessages,
    private val authRepository: AuthRepository
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

        if (!loginFormState.value.isEmailValid)
            return

        viewModelScope.launch {
            val result = authRepository.loginWithEmail(
                LoginInformation(
                    _loginFormState.value.email,
                    _loginFormState.value.password
                )
            )
            Log.d("login_result", result.toString())
            when(result) {
                is Result.Error -> _loginEvents.send(LoginEvents.ShowMessage(errorMessages.getErrorMessage(result.error)))
                is Result.Success -> _loginEvents.send(LoginEvents.LoginSuccessful(authRepository.getCurrentUser()?.isEmailVerified == true))
            }
        }
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