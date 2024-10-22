package com.felipeschoffen.onbudget.ui.financial_account.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.felipeschoffen.onbudget.R

@Composable
fun BalanceInput(balance: String, onBalanceChanged: (String) -> Unit) {

    TextField(
        value = balance,
        onValueChange = {
            if (it.startsWith("0")) {
                onBalanceChanged("")
            } else {
                onBalanceChanged(it)
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