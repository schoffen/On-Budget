package com.felipeschoffen.montrabudgetapp.sign_up.ui

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
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.EmailOutlinedTextField
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.LabeledOutlinedTextField
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.PasswordOutlinedTextField

@Composable
fun SignUpInputs(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        var nameValue by remember { mutableStateOf("") }
        LabeledOutlinedTextField(
            onValueChanged = { nameValue = it },
            value = nameValue,
            placeholder = stringResource(R.string.name_placeholder)
        )

        var emailValue by remember { mutableStateOf("") }
        EmailOutlinedTextField(
            onValueChanged = { emailValue = it },
            value = emailValue,
            placeholder = stringResource(R.string.email_placeholder)
        )

        var passwordValue by remember { mutableStateOf("") }
        var visible by remember { mutableStateOf(false) }
        PasswordOutlinedTextField(
            onValueChanged = { passwordValue = it },
            value = passwordValue,
            placeholder = stringResource(R.string.password_placeholder),
            toggleVisibility = {visible =! visible},
            isVisible = visible
        )
    }
}