package com.felipeschoffen.montrabudgetapp.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.CenteredTopAppBarWithBackAction

@Composable
fun LoginTopAppBar() {
    CenteredTopAppBarWithBackAction(
        onBackPressed = {

        },
        title = stringResource(R.string.login)
    )
}