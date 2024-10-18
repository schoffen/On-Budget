package com.felipeschoffen.onbudget

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipeschoffen.onbudget.core.Result
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
    val mainUIState get() = _mainUIState.value

    private val _userInformation = mutableStateOf<FirebaseUser?>(null)
    val userInformation get() = _userInformation.value

    init {
        getUserInformation()
    }

    private fun getUserInformation() {
        viewModelScope.launch {
            when(val result = authRepository.getUserInformation()) {
                is Result.Error -> _userInformation.value = null
                is Result.Success -> _userInformation.value = result.data
            }

            _mainUIState.value = _mainUIState.value.copy(isLoading = false)
        }
    }
}

data class MainUIState(
    val isLoading: Boolean = true
)