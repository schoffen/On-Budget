package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun LabeledOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String,
    placeholder: String
) {
    OutlinedTextField(
        modifier = defaultTextFieldModifier(),
        textStyle = defaultTextStyle(),
        colors = defaultTextFieldColors(),
        shape = defaultTextFieldShape(),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { defaultTextFieldPlaceholder(placeholder) }
    )
}