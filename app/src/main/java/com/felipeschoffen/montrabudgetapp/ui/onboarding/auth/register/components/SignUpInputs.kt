package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components

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
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.EmailOutlinedTextField
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.LabeledOutlinedTextField
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.PasswordOutlinedTextField

@Composable
fun SignUpInputs(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        LabeledOutlinedTextField(
            onValueChanged = onNameChange,
            value = name,
            placeholder = stringResource(R.string.name_hint)
        )

        EmailOutlinedTextField(
            onValueChanged = onEmailChange,
            value = email
        )

        var visible by remember { mutableStateOf(false) }

        PasswordOutlinedTextField(
            onValueChanged = onPasswordChange,
            value = password,
            toggleVisibility = {visible =! visible},
            isVisible = visible
        )
    }
}