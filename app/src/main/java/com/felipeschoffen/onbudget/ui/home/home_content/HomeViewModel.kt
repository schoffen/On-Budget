package com.felipeschoffen.onbudget.ui.home.home_content

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.felipeschoffen.onbudget.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    authRepository: AuthRepository
): ViewModel() {

    private val _state = mutableStateOf(HomeUiState())
    val state get() = _state
}