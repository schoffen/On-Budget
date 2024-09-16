package com.felipeschoffen.montrabudgetapp.forgot_password.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.CenteredTopAppBarWithBackAction

@Composable
fun ForgotPasswordTopAppBar(modifier: Modifier = Modifier) {
    CenteredTopAppBarWithBackAction(
        onBackPressed = {},
        title = stringResource(R.string.forgot_password_2)
    )
}