package com.felipeschoffen.onbudget.ui.core.inputs

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.onbudget.R

@Composable
fun EmailOutlinedTextField(
    onValueChanged: (String) -> Unit,
    value: String,
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
        placeholder = { CustomTextFieldPlaceholder(stringResource(R.string.email_hint)) },
        keyboardOptions = customEmailKeyboardOptions(),
        isError = isError,
        supportingText = {
            if (errorMessage != null)
                Text(text = errorMessage)
        }
    )
}