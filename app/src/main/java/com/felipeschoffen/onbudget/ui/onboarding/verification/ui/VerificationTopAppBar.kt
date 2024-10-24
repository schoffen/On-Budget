package com.felipeschoffen.onbudget.ui.onboarding.verification.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.CenteredTopAppBarWithBackAction

@Composable
fun VerificationTopAppBar(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    CenteredTopAppBarWithBackAction(
        modifier = modifier,
        onBackPressed = onBackPressed, title = stringResource(
            R.string.verification_title
        )
    )
}