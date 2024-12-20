package com.felipeschoffen.onbudget.ui.onboarding.pin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.core.util.errors.toString
import com.felipeschoffen.onbudget.core.util.onError
import com.felipeschoffen.onbudget.core.util.onSuccess
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {
    private val _pinUIState = mutableStateOf(PinUIState())
    val pinUIState = _pinUIState

    private var _events = Channel<PinEvents>()
    val events = _events.receiveAsFlow()

    private var _step = mutableStateOf(PinStep.SETUP_NEW)
    val step get() = _step

    private lateinit var userPin: String

    private var _inputValue = mutableStateOf("")
    val inputValue = _inputValue

    init {
        viewModelScope.launch {
            authRepository.getUserInformation()
                .onSuccess { data ->
                    if (data?.registrationStep == RegistrationStep.SETUP_PIN)
                        _step.value = PinStep.SETUP_NEW

                    if (data?.registrationStep == RegistrationStep.COMPLETE) {
                        _step.value = PinStep.AUTHENTICATE
                    }
                }
                .onError { error ->
                    _events.send(PinEvents.ShowMessage(error.toString(resourceProvider)))
                }

            _pinUIState.value = _pinUIState.value.copy(isLoading = false)
        }
    }

    fun onValueChanged(value: String) {
        _inputValue.value = value
    }

    fun sendPin() {
        viewModelScope.launch {
            when (_step.value) {
                PinStep.SETUP_NEW -> {
                    userPin = _inputValue.value
                    _inputValue.value = ""
                    _step.value = PinStep.CONFIRM_NEW
                }

                PinStep.CONFIRM_NEW -> {
                    when (_inputValue.value == userPin) {
                        true -> {
                            authRepository.registerPin(userPin)
                                .onSuccess {
                                    _events.send(PinEvents.SuccessfullyChecked)
                                }
                                .onError { error ->
                                    _events.send(
                                        PinEvents.ShowMessage(
                                            error.toString(
                                                resourceProvider
                                            )
                                        )
                                    )
                                }
                        }

                        false -> {
                            _step.value = PinStep.SETUP_NEW
                            _events.send(PinEvents.CheckFailed)
                            onValueChanged("")
                        }
                    }
                }

                PinStep.AUTHENTICATE -> {
                    authRepository.pinAuthentication(_inputValue.value)
                        .onSuccess {
                            _events.send(PinEvents.SuccessfullyChecked)
                        }
                        .onError {
                            _events.send(PinEvents.CheckFailed)
                            onValueChanged("")
                        }
                }
            }
        }
    }
}