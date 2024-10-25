package com.felipeschoffen.onbudget.ui.financial_account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.util.errors.NameInputError
import com.felipeschoffen.onbudget.core.util.errors.toString
import com.felipeschoffen.onbudget.core.util.onError
import com.felipeschoffen.onbudget.core.util.onSuccess
import com.felipeschoffen.onbudget.data.model.AccountType
import com.felipeschoffen.onbudget.data.model.FinancialAccount
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import com.felipeschoffen.onbudget.domain.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    data class UIState(
        val account: FinancialAccount = FinancialAccount(),
        val isNameValid: Boolean = true,
        val nameErrorMessage: String? = null,
        val isLoading: Boolean = false
    )

    private val _events = Channel<CreateAccountEvents>()
    val events = _events.receiveAsFlow()

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
            authRepository.createFinancialAccount(_uiState.value.account)
                .onSuccess {
                    _events.send(CreateAccountEvents.CreateSuccessful)
                }
                .onError { error ->
                    _events.send(CreateAccountEvents.ShowMessage(error.toString(resourceProvider)))
                }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    private fun validateName() {
        _uiState.value =
            _uiState.value.copy(
                isNameValid = _uiState.value.account.name.isNotEmpty(),
                nameErrorMessage = if (_uiState.value.isNameValid) null else NameInputError.BLANK.toString(
                    resourceProvider
                )
            )
    }
}