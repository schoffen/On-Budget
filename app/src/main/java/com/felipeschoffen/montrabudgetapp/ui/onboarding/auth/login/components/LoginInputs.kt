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

@Composable
fun LoginInputs(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        var emailValue by remember { mutableStateOf("") }

        EmailOutlinedTextField(
            onValueChanged = { emailValue = it },
            value = emailValue
        )

        var passwordValue by remember { mutableStateOf("") }
        var visible by remember { mutableStateOf(false) }

        PasswordOutlinedTextField(
            onValueChanged = { passwordValue = it },
            value = passwordValue,
            toggleVisibility = {visible =! visible},
            isVisible = visible
        )
    }
}