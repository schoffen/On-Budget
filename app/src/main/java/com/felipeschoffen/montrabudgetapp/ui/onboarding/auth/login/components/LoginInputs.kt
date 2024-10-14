package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.EmailOutlinedTextField
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.PasswordOutlinedTextField
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.LoginFormState

@Composable
fun LoginInputs(
    loginFormState: LoginFormState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        EmailOutlinedTextField(
            onValueChanged = onEmailChange,
            value = loginFormState.email,
            isError = !loginFormState.isEmailValid,
            errorMessage = loginFormState.emailErrorMessage
        )

        var visible by remember { mutableStateOf(false) }

        PasswordOutlinedTextField(
            onValueChanged = onPasswordChange,
            value = loginFormState.password,
            toggleVisibility = {visible =! visible},
            isVisible = visible
        )
    }
}