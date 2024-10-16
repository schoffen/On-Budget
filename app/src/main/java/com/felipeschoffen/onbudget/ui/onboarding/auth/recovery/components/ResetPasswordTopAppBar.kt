package com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.CenteredTopAppBarWithBackAction

@Composable
fun ResetPasswordTopAppBar(modifier: Modifier = Modifier) {
    CenteredTopAppBarWithBackAction(
        modifier = modifier,
        onBackPressed = {},
        title = stringResource(R.string.reset_password_title)
    )
}