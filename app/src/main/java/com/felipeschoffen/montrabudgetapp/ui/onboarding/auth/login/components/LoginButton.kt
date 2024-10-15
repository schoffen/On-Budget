package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.ui.core.buttons.CustomButtonPrimary

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean
) {
    CustomButtonPrimary(onClick = onClick, text = stringResource(R.string.login_title), modifier = modifier, showLoadingProgress = isLoading)
}