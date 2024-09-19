package com.felipeschoffen.montrabudgetapp.sign_up.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.CenteredTopAppBarWithBackAction

@Composable
fun SignUpTopAppBar() {
    CenteredTopAppBarWithBackAction(
        onBackPressed = {

        },
        title = stringResource(R.string.sign_up_title)
    )
}