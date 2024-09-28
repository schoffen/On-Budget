package com.felipeschoffen.montrabudgetapp.reset_password.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.recovery.components.ResetPasswordInputs
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.recovery.components.ResetPasswordTopAppBar

@Composable
fun ResetPasswordScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier,
        topBar = {
            ResetPasswordTopAppBar()
        }) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .imePadding()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center,

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                ResetPasswordInputs()

                CustomButtonPrimary(
                    onClick = {

                    },
                    text = stringResource(R.string.action_continue)
                )
            }
        }
    }
}