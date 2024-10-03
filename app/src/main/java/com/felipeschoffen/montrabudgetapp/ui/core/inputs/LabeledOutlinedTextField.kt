package com.felipeschoffen.montrabudgetapp.ui.core.inputs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LabeledOutlinedTextField(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    value: String,
    placeholder: String,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier.customOutlinedTextFieldModifier(),
        textStyle = customTextStyle(),
        colors = customOutlinedTextFieldColors(),
        shape = customOutlinedTextFieldShape(),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { CustomTextFieldPlaceholder(placeholder) },
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        isError = isError,
        supportingText = {
            if (errorMessage != null)
                Text(text = errorMessage)
        }
    )
}