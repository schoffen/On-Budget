package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.recovery.components

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
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.PasswordOutlinedTextField

@Composable
fun ResetPasswordInputs(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        var newPassword by remember { mutableStateOf("") }
        var newPasswordVisible by remember { mutableStateOf(false) }

        PasswordOutlinedTextField(
            onValueChanged = {
                newPassword = it
            },
            value = newPassword,
            placeholder = stringResource(R.string.new_password_hint),
            toggleVisibility = { newPasswordVisible = !newPasswordVisible },
            isVisible = newPasswordVisible
        )

        var confirmNewPassword by remember { mutableStateOf("") }
        var confirmNewPasswordVisible by remember { mutableStateOf(false) }

        PasswordOutlinedTextField(
            onValueChanged = {
                confirmNewPassword = it
            },
            placeholder = stringResource(R.string.confirm_password_hint),
            value = confirmNewPassword,
            toggleVisibility = { confirmNewPasswordVisible = !confirmNewPasswordVisible },
            isVisible = confirmNewPasswordVisible
        )
    }
}