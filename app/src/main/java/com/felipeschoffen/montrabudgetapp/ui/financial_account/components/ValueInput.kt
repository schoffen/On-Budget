package com.felipeschoffen.montrabudgetapp.ui.financial_account.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.felipeschoffen.montrabudgetapp.R

@Composable
fun ValueInput() {
    var value by remember {
        mutableStateOf("")
    }

    TextField(
        value = value,
        onValueChange = {
            value = if (it.startsWith("0")) {
                ""
            } else {
                it
            }
        },
        visualTransformation = ValueInputVisualTransformation(
            fixedCursorAtTheEnd = true
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedPrefixColor = Color.White,
            focusedPrefixColor = Color.White
        ),
        textStyle = MaterialTheme.typography.titleLarge,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        leadingIcon = {
            Text(
                text = stringResource(id = R.string.currency_prefix),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        },
        singleLine = true
    )
}