package com.felipeschoffen.montrabudgetapp.sign_up.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.LabeledOutlinedTextField

@Composable
internal fun SignUpInputs(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        var value by remember { mutableStateOf("") }
        LabeledOutlinedTextField(onValueChanged = { value = it }, value = value, placeholder = "Name")

    }
}