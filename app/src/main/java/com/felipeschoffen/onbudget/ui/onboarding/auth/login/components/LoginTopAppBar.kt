package com.felipeschoffen.onbudget.ui.onboarding.auth.login.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.CenteredTopAppBarWithBackAction

@Composable
fun LoginTopAppBar(
    onBackPressed: () -> Unit
) {
    CenteredTopAppBarWithBackAction(
        onBackPressed = onBackPressed,
        title = stringResource(R.string.login_title)
    )
}