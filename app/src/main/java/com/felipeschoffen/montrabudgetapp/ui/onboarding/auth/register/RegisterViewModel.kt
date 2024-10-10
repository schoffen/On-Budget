package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.montrabudgetapp.core.Result
import com.felipeschoffen.montrabudgetapp.core.error.RegisterError
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.domain.validations.EmailValidator
import com.felipeschoffen.montrabudgetapp.domain.validations.NameValidator
import com.felipeschoffen.montrabudgetapp.domain.validations.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val nameValidator: NameValidator,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val errorMessages: ErrorMessages
) : ViewModel() {

    private val _registerEvents = Channel<RegisterEvents>()
    val registerEvents = _registerEvents.receiveAsFlow()

    private var _registerFormState = mutableStateOf(RegisterFormState())
    val registerFormState get() = _registerFormState

    fun onNameChange(newName: String) {
        _registerFormState.value = _registerFormState.value.copy(name = newName)
    }

    fun onEmailChange(newEmail: String) {
        _registerFormState.value = _registerFormState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _registerFormState.value = _registerFormState.value.copy(password = newPassword)
    }

    fun onTermsChecked() {
        _registerFormState.value =
            _registerFormState.value.copy(isTermsChecked = !_registerFormState.value.isTermsChecked)
    }

    fun registerWithEmail() {
        validateName()
        validateEmail()
        validatePassword()

        if (!_registerFormState.value.isTermsChecked) {
            viewModelScope.launch {
                _registerEvents.send(
                    RegisterEvents.ShowMessage(
                        errorMessages.getMessage(
                            RegisterError.TERMS_NOT_ACCEPTED
                        )
                    )
                )
            }

            return
        }

        if (_registerFormState.value.isNameValid && _registerFormState.value.isEmailValid && _registerFormState.value.isPasswordValid) {
            viewModelScope.launch {
                val result = authRepository.registerWithEmail(
                    RegistrationInfo(
                        name = _registerFormState.value.name,
                        email = _registerFormState.value.email,
                        password = _registerFormState.value.password
                    )
                )

                when (result) {
                    is Result.Error -> {
                        _registerEvents.send(
                            RegisterEvents.ShowMessage(
                                errorMessages.getMessage(
                                    result.error
                                )
                            )
                        )
                    }

                    is Result.Success -> _registerEvents.send(RegisterEvents.RegisterSuccessful)
                }
            }
        }
    }

    private fun validateName() {
        val isValid: Boolean
        val errorMessage: String?

        when (val result = nameValidator.execute(_registerFormState.value.name)) {
            is Result.Error -> {
                isValid = false
                errorMessage = errorMessages.getMessage(result.error)
            }

            is Result.Success -> {
                isValid = true
                errorMessage = null
            }
        }

        _registerFormState.value = _registerFormState.value.copy(
            isNameValid = isValid,
            nameErrorMessage = errorMessage
        )
    }

    private fun validateEmail() {
        val isValid: Boolean
        val errorMessage: String?

        when (val result = emailValidator.execute(_registerFormState.value.email)) {
            is Result.Error -> {
                isValid = false
                errorMessage = errorMessages.getMessage(result.error)
            }

            is Result.Success -> {
                isValid = true
                errorMessage = null
            }
        }

        _registerFormState.value = _registerFormState.value.copy(
            isEmailValid = isValid,
            emailErrorMessage = errorMessage
        )
    }

    private fun validatePassword() {
        val isValid: Boolean
        val errorMessage: String?

        when (val result = passwordValidator.execute(_registerFormState.value.password)) {
            is Result.Error -> {
                isValid = false
                errorMessage = errorMessages.getMessage(result.error)
            }

            is Result.Success -> {
                isValid = true
                errorMessage = null
            }
        }

        _registerFormState.value = _registerFormState.value.copy(
            isPasswordValid = isValid,
            passwordErrorMessage = errorMessage
        )
    }
}