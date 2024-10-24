package com.felipeschoffen.onbudget.ui.onboarding.pin

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.RegistrationStep
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ErrorMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val errorMessages: ErrorMessages
) : ViewModel() {
    private val _pinUIState = mutableStateOf(PinUIState())
    val pinUIState = _pinUIState

    private var _pinEvents = Channel<PinEvents>()
    val pinEvents = _pinEvents.receiveAsFlow()

    private var _step = mutableStateOf(PinStep.SETUP_NEW)
    val step get() = _step

    private lateinit var userPin: String

    private var _inputValue = mutableStateOf("")
    val inputValue = _inputValue

    init {
        viewModelScope.launch {
            val result = authRepository.getUserInformation()
            if (result is Result.Success) {
                if (result.data?.registrationStep == RegistrationStep.SETUP_PIN)
                    _step.value = PinStep.SETUP_NEW

                if (result.data?.registrationStep == RegistrationStep.COMPLETE) {
                    _step.value = PinStep.AUTHENTICATE
                }

                _pinUIState.value = _pinUIState.value.copy(isLoading = false)
            }
        }
    }

    fun onValueChanged(value: String) {
        _inputValue.value = value
        Log.d("vm", _inputValue.value)
    }

    fun sendPin() {
        when (_step.value) {
            PinStep.SETUP_NEW -> {
                userPin = _inputValue.value
                _inputValue.value = ""
                _step.value = PinStep.CONFIRM_NEW
            }

            PinStep.CONFIRM_NEW -> {
                when (_inputValue.value == userPin) {
                    true -> viewModelScope.launch {
                        val result = authRepository.registerPin(userPin)
                        Log.d("pin", result.toString())
                        _pinEvents.send(PinEvents.SuccessfullyChecked)
                    }

                    false -> viewModelScope.launch {
                        _step.value = PinStep.SETUP_NEW
                        _pinEvents.send(PinEvents.CheckFailed)
                        onValueChanged("")
                    }
                }
            }

            PinStep.AUTHENTICATE -> viewModelScope.launch {
                when (authRepository.pinAuthentication(_inputValue.value)) {
                    is Result.Error -> {
                        _pinEvents.send(PinEvents.CheckFailed)
                        onValueChanged("")
                    }
                    is Result.Success -> _pinEvents.send(PinEvents.SuccessfullyChecked)
                }
            }
        }
    }
}