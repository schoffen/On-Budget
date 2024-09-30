package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private var _registerState = mutableStateOf(RegisterState())
    val registerState get() = _registerState

    fun onNameChange(newName: String) {
        _registerState.value = _registerState.value.copy(name = newName)
    }

    fun onEmailChange(newEmail: String) {
        _registerState.value = _registerState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _registerState.value = _registerState.value.copy(password = newPassword)
    }
}