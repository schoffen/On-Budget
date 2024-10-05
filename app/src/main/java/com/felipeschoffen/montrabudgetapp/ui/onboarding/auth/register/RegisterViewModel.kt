package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.montrabudgetapp.data.model.RegistrationInfo
import com.felipeschoffen.montrabudgetapp.domain.auth.RegisterResult
import com.felipeschoffen.montrabudgetapp.domain.repository.AuthRepository
import com.felipeschoffen.montrabudgetapp.domain.validations.EmailValidator
import com.felipeschoffen.montrabudgetapp.domain.validations.NameValidator
import com.felipeschoffen.montrabudgetapp.domain.validations.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val nameValidator: NameValidator,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
) : ViewModel() {

    private var _registerFormState = mutableStateOf(RegisterFormState())
    val registerFormState get() = _registerFormState

    val registerResult: MutableState<RegisterResult> get() = authRepository.registerResult

    fun onNameChange(newName: String) {
        _registerFormState.value = _registerFormState.value.copy(name = newName)
    }

    fun onEmailChange(newEmail: String) {
        _registerFormState.value = _registerFormState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _registerFormState.value = _registerFormState.value.copy(password = newPassword)
    }

    fun registerWithEmail() {
        validateName()
        validateEmail()
        validatePassword()

        if (_registerFormState.value.isNameValid && _registerFormState.value.isEmailValid && _registerFormState.value.isPasswordValid) {
            viewModelScope.launch {
                authRepository.registerWithEmail(RegistrationInfo(
                    name = _registerFormState.value.name,
                    email = _registerFormState.value.email,
                    password = _registerFormState.value.password
                ))
            }
        }
    }

    private fun validateName() {
        val result = nameValidator.execute(_registerFormState.value.name)

        _registerFormState.value = _registerFormState.value.copy(
            isNameValid = result.successful,
            nameErrorMessage = result.errorMessage
        )
    }

    private fun validateEmail() {
        val result = emailValidator.execute(_registerFormState.value.email)

        _registerFormState.value = _registerFormState.value.copy(
            isEmailValid = result.successful,
            emailErrorMessage = result.errorMessage
        )
    }

    private fun validatePassword() {
        val result = passwordValidator.execute(_registerFormState.value.password)

        _registerFormState.value = _registerFormState.value.copy(
            isPasswordValid = result.successful,
            passwordErrorMessage = result.errorMessage
        )
    }
}