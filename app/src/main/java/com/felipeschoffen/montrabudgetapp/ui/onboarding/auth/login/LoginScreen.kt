package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components.ForgotPasswordTextButton
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components.LoginButton
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components.LoginInputs
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components.LoginTopAppBar
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.login.components.GoToRegisterText

@Composable
fun LoginScreen(
    onBackPressed: () -> Unit,
    onSignUpClicked: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier,
        topBar = {
            LoginTopAppBar(onBackPressed = onBackPressed)
        }
    ) { paddingValues ->
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
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                LoginInputs()

                LoginButton(onClick = {

                })

                ForgotPasswordTextButton(onClick = onForgotPasswordClicked)

                GoToRegisterText(
                    onSignUpClicked = onSignUpClicked
                )
            }
        }
    }
}

