package com.felipeschoffen.onbudget.ui.financial_account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.Result
import com.felipeschoffen.onbudget.core.error.NameInputError
import com.felipeschoffen.onbudget.data.model.AccountType
import com.felipeschoffen.onbudget.data.model.FinancialAccount
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ErrorMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val errorMessages: ErrorMessages
) : ViewModel() {

    data class UIState(
        val account: FinancialAccount = FinancialAccount(),
        val isNameValid: Boolean = true,
        val nameErrorMessage: String? = null,
        val isLoading: Boolean = false
    )

    private val _uiEvents = Channel<CreateAccountEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    private val _uiState = mutableStateOf(UIState())
    val uiState get() = _uiState

    fun onNameChanged(name: String) {
        _uiState.value =
            _uiState.value.copy(account = _uiState.value.account.copy(name = name))
    }

    fun onBalanceChanged(balance: String) {
        _uiState.value =
            _uiState.value.copy(account = _uiState.value.account.copy(balance = balance))
    }

    fun onTypeChanged(type: AccountType) {
        _uiState.value =
            _uiState.value.copy(account = _uiState.value.account.copy(type = type))
    }

    fun onContinueClicked() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        validateName()
        if (!_uiState.value.isNameValid) {
            _uiState.value = _uiState.value.copy(isLoading = false)
            return
        }

        viewModelScope.launch {
            when (val result = authRepository.createFinancialAccount(uiState.value.account)) {
                is Result.Error -> _uiEvents.send(
                    CreateAccountEvents.ShowMessage(
                        errorMessages.getErrorMessage(
                            result.error
                        )
                    )
                )

                is Result.Success -> _uiEvents.send(CreateAccountEvents.CreateSuccessful)
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    private fun validateName() {
        _uiState.value =
            _uiState.value.copy(isNameValid = _uiState.value.account.name.isNotEmpty())
        _uiState.value = _uiState.value.copy(
            nameErrorMessage = if (_uiState.value.isNameValid) null else errorMessages.getErrorMessage(
                NameInputError.BLANK
            )
        )
    }
}