package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.domain.util.InputError
import com.felipeschoffen.montrabudgetapp.domain.util.ResourceProvider
import com.felipeschoffen.montrabudgetapp.domain.validations.ValidateEmail
import com.felipeschoffen.montrabudgetapp.domain.validations.ValidateName
import com.felipeschoffen.montrabudgetapp.domain.validations.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

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

    fun register() {
        validateName()
        validateEmail()
        validatePassword()
    }

    private fun validateName() {
        val result = ValidateName().execute(_registerFormState.value.name)

        _registerFormState.value = _registerFormState.value.copy(
            isNameValid = result.successful,
            nameErrorMessage = result.error?.let { error ->
                when (error) {
                    InputError.Blank -> resourceProvider.getString(R.string.error_blank_input)
                    InputError.ShortLength -> resourceProvider.getString(R.string.error_short_name)
                    else -> null
                }
            }
        )
    }

    private fun validateEmail() {
        val result = ValidateEmail().execute(_registerFormState.value.email)

        _registerFormState.value = _registerFormState.value.copy(
            isEmailValid = result.successful,
            emailErrorMessage = result.error?.let { error ->
                when (error) {
                    InputError.Blank -> resourceProvider.getString(R.string.error_blank_input)
                    InputError.Invalid -> resourceProvider.getString(R.string.error_invalid_email)
                    else -> null
                }
            }
        )
    }

    private fun validatePassword() {
        val result = ValidatePassword().execute(_registerFormState.value.password)

        _registerFormState.value = _registerFormState.value.copy(
            isPasswordValid = result.successful,
            passwordErrorMessage = result.error?.let { error ->
                when (error) {
                    InputError.Blank -> resourceProvider.getString(R.string.error_blank_input)
                    InputError.ShortLength -> resourceProvider.getString(R.string.error_short_password)
                    else -> null
                }
            }
        )
    }
}