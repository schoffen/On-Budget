package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EmailOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String,
    placeholder: String
) {
    OutlinedTextField(
        modifier = Modifier.customOutlinedTextFieldModifier(),
        textStyle = customTextStyle(),
        colors = customOutlinedTextFieldColors(),
        shape = customOutlinedTextFieldShape(),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { CustomTextFieldPlaceholder(placeholder) },
        keyboardOptions = customEmailKeyboardOptions()
    )
}