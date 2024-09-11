package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String,
    placeholder: String,
    toggleVisibility: () -> Unit,
    isVisible: Boolean
) {
    OutlinedTextField(
        modifier = Modifier.customOutlinedTextFieldModifier(),
        textStyle = customTextStyle(),
        colors = customOutlinedTextFieldColors(),
        shape = customOutlinedTextFieldShape(),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { CustomTextFieldPlaceholder(placeholder) },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = customPasswordKeyboardOptions(),
        trailingIcon = {
            IconButton(toggleVisibility) {
                if (isVisible) CustomPasswordVisibilityOnTrailingIcon() else CustomPasswordVisibilityOffTrailingIcon()
            }
        }
    )
}