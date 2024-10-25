package com.felipeschoffen.onbudget.ui.onboarding.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ErrorMessages
import com.felipeschoffen.onbudget.domain.validations.EmailValidator
import com.felipeschoffen.onbudget.domain.validations.NameValidator
import com.felipeschoffen.onbudget.domain.validations.PasswordValidator
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
    private var _requestLoading = mutableStateOf(false)
    val requestLoading get() = _requestLoading.value

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
        _requestLoading.value = true

        validateName()
        validateEmail()
        validatePassword()

        if (!_registerFormState.value.isTermsChecked) {
            _requestLoading.value = false
            viewModelScope.launch {
                _registerEvents.send(
                    RegisterEvents.ShowMessage(
                        errorMessages.getErrorMessage(
                            RegisterError.TERMS_NOT_ACCEPTED
                        )
                    )
                )
            }
            return
        }

        if (!_registerFormState.value.isNameValid ||
            !_registerFormState.value.isEmailValid ||
            !_registerFormState.value.isPasswordValid
        ) {
            _requestLoading.value = false
            return
        }

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
                            errorMessages.getErrorMessage(
                                result.error
                            )
                        )
                    )
                    _requestLoading.value = false
                }

                is Result.Success -> {
                    _registerEvents.send(RegisterEvents.RegisterSuccessful)
                    _requestLoading.value = false
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
                errorMessage = errorMessages.getErrorMessage(result.error)
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
                errorMessage = errorMessages.getErrorMessage(result.error)
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
                errorMessage = errorMessages.getErrorMessage(result.error)
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