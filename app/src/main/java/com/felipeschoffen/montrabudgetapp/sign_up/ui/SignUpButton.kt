package com.felipeschoffen.montrabudgetapp.sign_up.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.buttons.CustomButtonPrimary

@Composable
fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButtonPrimary(
        onClick = onClick,
        text = stringResource(R.string.sign_up),
        modifier = modifier
    )
}