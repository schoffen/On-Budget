package com.felipeschoffen.montrabudgetapp.verification.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.CenteredTopAppBarWithBackAction

@Composable
fun VerificationTopAppBar(modifier: Modifier = Modifier) {
    CenteredTopAppBarWithBackAction(
        modifier = modifier,
        onBackPressed = {

        }, title = stringResource(
            R.string.verification
        )
    )
}