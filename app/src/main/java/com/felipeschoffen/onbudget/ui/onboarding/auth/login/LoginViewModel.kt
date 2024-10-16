package com.felipeschoffen.onbudget.ui.onboarding.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ErrorMessages
import com.felipeschoffen.onbudget.domain.validations.EmailValidator
import com.felipeschoffen.onbudget.domain.validations.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val errorMessages: ErrorMessages,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _isLoading = mutableStateOf(false)
    val isLoading get() = _isLoading.value

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
        _isLoading.value = true

        validateEmail()
        validatePassword()

        if (!loginFormState.value.isEmailValid || !loginFormState.value.isPasswordValid) {
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            val result = authRepository.loginWithEmail(
                LoginInformation(
                    _loginFormState.value.email,
                    _loginFormState.value.password
                )
            )

            _isLoading.value = false

            when (result) {
                is Result.Error -> _loginEvents.send(
                    LoginEvents.ShowMessage(
                        errorMessages.getErrorMessage(
                            result.error
                        )
                    )
                )

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

    private fun validatePassword() {
        val isValid: Boolean
        val errorMessage: String?

        when (val result = passwordValidator.execute(_loginFormState.value.password)) {
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
            isPasswordValid = isValid,
            passwordErrorMessage = errorMessage
        )
    }
}