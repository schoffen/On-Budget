package com.felipeschoffen.onbudget

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.util.onError
import com.felipeschoffen.onbudget.core.util.onSuccess
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _mainUIState = mutableStateOf(MainUIState())
    val mainUIState get() = _mainUIState

    private val _userInformation = mutableStateOf<FirebaseUser?>(null)
    val userInformation get() = _userInformation

    init {
        getUserInformation()
    }

    private fun getUserInformation() {
        viewModelScope.launch {
            authRepository.getUserInformation()
                .onSuccess { user ->
                    _userInformation.value = user
                }
                .onError {
                    _userInformation.value = null
                }

            _mainUIState.value = _mainUIState.value.copy(isLoading = false)
        }
    }
}

data class MainUIState(
    val isLoading: Boolean = true
)