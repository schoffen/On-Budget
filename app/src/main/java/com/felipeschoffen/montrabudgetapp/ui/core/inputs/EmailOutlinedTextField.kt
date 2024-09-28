package com.felipeschoffen.montrabudgetapp.ui.core.inputs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R

@Composable
fun EmailOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String
) {
    OutlinedTextField(
        modifier = Modifier.customOutlinedTextFieldModifier(),
        textStyle = customTextStyle(),
        colors = customOutlinedTextFieldColors(),
        shape = customOutlinedTextFieldShape(),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { CustomTextFieldPlaceholder(stringResource(R.string.email_hint)) },
        keyboardOptions = customEmailKeyboardOptions()
    )
}