package com.felipeschoffen.onbudget.ui.onboarding.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.error.RegisterError
import com.felipeschoffen.onbudget.core.error.toString
import com.felipeschoffen.onbudget.core.onError
import com.felipeschoffen.onbudget.core.onSuccess
import com.felipeschoffen.onbudget.data.model.RegistrationInfo
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
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
    private val resourceProvider: ResourceProvider
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

        if (!validateRegisterForm() || !validateTerms()) {
            _requestLoading.value = false
            return
        }

        viewModelScope.launch {
            authRepository.registerWithEmail(
                RegistrationInfo(
                    name = _registerFormState.value.name,
                    email = _registerFormState.value.email,
                    password = _registerFormState.value.password
                )
            ).onError { error ->
                _registerEvents.send(RegisterEvents.ShowMessage(error.toString(resourceProvider)))
            }.onSuccess {
                _registerEvents.send(RegisterEvents.RegisterSuccessful)
            }

            _requestLoading.value = false
        }
    }

    private fun validateRegisterForm(): Boolean {
        nameValidator.execute(_registerFormState.value.name)
            .onError { error ->
                _registerFormState.value = _registerFormState.value.copy(
                    isNameValid = false,
                    nameErrorMessage = error.toString(resourceProvider)
                )
            }
            .onSuccess {
                _registerFormState.value = _registerFormState.value.copy(
                    isNameValid = true,
                    nameErrorMessage = null
                )
            }

        emailValidator.execute(_registerFormState.value.email)
            .onError { error ->
                _registerFormState.value = _registerFormState.value.copy(
                    isEmailValid = false,
                    emailErrorMessage = error.toString(resourceProvider)
                )
            }
            .onSuccess {
                _registerFormState.value = _registerFormState.value.copy(
                    isEmailValid = true,
                    emailErrorMessage = null
                )
            }

        passwordValidator.execute(_registerFormState.value.password)
            .onError { error ->
                _registerFormState.value = _registerFormState.value.copy(
                    isPasswordValid = false,
                    passwordErrorMessage = error.toString(resourceProvider)
                )
            }
            .onSuccess {
                _registerFormState.value = _registerFormState.value.copy(
                    isPasswordValid = true,
                    passwordErrorMessage = null
                )
            }

        return (_registerFormState.value.isNameValid &&
                _registerFormState.value.isEmailValid &&
                _registerFormState.value.isPasswordValid)
    }

    private fun validateTerms(): Boolean {
        if (!_registerFormState.value.isTermsChecked) {
            _requestLoading.value = false
            viewModelScope.launch {
                _registerEvents.send(
                    RegisterEvents.ShowMessage(
                        RegisterError.TERMS_NOT_ACCEPTED.toString(resourceProvider)
                    )
                )
            }
        }

        return _registerFormState.value.isTermsChecked
    }
}