package com.felipeschoffen.onbudget.ui.onboarding.auth.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.inputs.EmailOutlinedTextField
import com.felipeschoffen.onbudget.ui.core.inputs.LabeledOutlinedTextField
import com.felipeschoffen.onbudget.ui.core.inputs.PasswordOutlinedTextField
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.RegisterFormState

@Composable
fun SignUpInputs(
    modifier: Modifier = Modifier,
    registerFormState: RegisterFormState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LabeledOutlinedTextField(
            onValueChanged = onNameChange,
            value = registerFormState.name,
            placeholder = stringResource(R.string.name_hint),
            isError = !registerFormState.isNameValid,
            errorMessage = registerFormState.nameErrorMessage
        )

        EmailOutlinedTextField(
            onValueChanged = onEmailChange,
            value = registerFormState.email,
            isError = !registerFormState.isEmailValid,
            errorMessage = registerFormState.emailErrorMessage
        )

        var visible by remember { mutableStateOf(false) }

        PasswordOutlinedTextField(
            onValueChanged = onPasswordChange,
            value = registerFormState.password,
            toggleVisibility = {visible =! visible},
            isVisible = visible,
            isError = !registerFormState.isPasswordValid,
            errorMessage = registerFormState.passwordErrorMessage
        )
    }
}