package com.felipeschoffen.onbudget.ui.onboarding.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.util.errors.toString
import com.felipeschoffen.onbudget.core.util.onError
import com.felipeschoffen.onbudget.core.util.onSuccess
import com.felipeschoffen.onbudget.data.model.LoginInformation
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
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
    private val resourceProvider: ResourceProvider,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _isLoading = mutableStateOf(false)
    val isLoading get() = _isLoading.value

    private val _loginFormState = mutableStateOf(LoginFormState())
    val loginFormState get() = _loginFormState

    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()

    fun onEmailChange(value: String) {
        _loginFormState.value = _loginFormState.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        _loginFormState.value = _loginFormState.value.copy(password = value)
    }

    fun login() {
        _isLoading.value = true

        if (!validateLoginForm()) {
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            authRepository.loginWithEmail(
                LoginInformation(
                    _loginFormState.value.email,
                    _loginFormState.value.password
                )
            ).onError { error ->
                _events.send(LoginEvents.ShowMessage(error.toString(resourceProvider)))
            }.onSuccess {
                authRepository.getUserInformation()
                    .onError { error ->
                        _events.send(LoginEvents.ShowMessage(error.toString(resourceProvider)))
                    }
                    .onSuccess { user ->
                        _events.send(LoginEvents.LoginSuccessful(user?.registrationStep!!))
                    }
            }

            _isLoading.value = false
        }
    }

    private fun validateLoginForm(): Boolean {
        emailValidator
            .execute(_loginFormState.value.email)
            .onError { error ->
                _loginFormState.value = _loginFormState.value.copy(
                    isEmailValid = false,
                    emailErrorMessage = error.toString(resourceProvider)
                )
            }
            .onSuccess {
                _loginFormState.value = _loginFormState.value.copy(
                    isEmailValid = true,
                    emailErrorMessage = null
                )
            }

        passwordValidator
            .execute(_loginFormState.value.password)
            .onError { error ->
                _loginFormState.value = _loginFormState.value.copy(
                    isPasswordValid = false,
                    passwordErrorMessage = error.toString(resourceProvider)
                )
            }
            .onSuccess {
                _loginFormState.value = _loginFormState.value.copy(
                    isPasswordValid = true,
                    passwordErrorMessage = null
                )
            }

        return (_loginFormState.value.isEmailValid && _loginFormState.value.isPasswordValid)
    }
}