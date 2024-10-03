package com.felipeschoffen.montrabudgetapp.ui.core.inputs

import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.felipeschoffen.montrabudgetapp.R

@Composable
fun PasswordOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String,
    placeholder: String = stringResource(R.string.password_hint),
    toggleVisibility: () -> Unit,
    isVisible: Boolean,
    isError: Boolean = false,
    errorMessage: String? = null,
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
        },
        isError = isError,
        supportingText = {
            if (errorMessage != null)
                Text(text = errorMessage)
        }
    )
}