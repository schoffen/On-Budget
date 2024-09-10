package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun PasswordOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String,
    placeholder: String,
    toggleVisibility: () -> Unit,
    isVisible: Boolean
) {
    OutlinedTextField(
        modifier = defaultTextFieldModifier(),
        textStyle = defaultTextStyle(),
        colors = defaultTextFieldColors(),
        shape = defaultTextFieldShape(),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { defaultTextFieldPlaceholder(placeholder) },
        keyboardOptions = defaultPasswordKeyboardOptions(),
        trailingIcon = {
            IconButton(toggleVisibility) {
                if (isVisible) defaultPasswordVisibilityOnTrailingIcon() else defaultPasswordVisibilityOffTrailingIcon()
            }
        }
    )
}