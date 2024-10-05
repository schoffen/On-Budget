package com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.ui.core.buttons.ButtonGoogleSignUp
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components.GoToLoginText
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components.SignUpButton
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components.SignUpInputs
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components.SignUpOrWithText
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components.SignUpPrivacyAgreement
import com.felipeschoffen.montrabudgetapp.ui.onboarding.auth.register.components.RegisterTopAppBar

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onLoginClicked: () -> Unit,
    onRegister: () -> Unit,
    registerViewModel: RegisterViewModel
) {
    val registerFormState by registerViewModel.registerFormState

    Scaffold(
        topBar = {
            RegisterTopAppBar(onBackPressed = onBackPressed)
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .imePadding()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
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
                SignUpInputs(
                    registerFormState = registerFormState,
                    onNameChange = { registerViewModel.onNameChange(it) },
                    onPasswordChange = { registerViewModel.onPasswordChange(it) },
                    onEmailChange = { registerViewModel.onEmailChange(it) }
                )

                var checked by remember { mutableStateOf(false) }
                SignUpPrivacyAgreement(checked = checked, onCheckedChange = { checked = !checked })

                SignUpButton(onClick = {
                    registerViewModel.registerWithEmail()
                })

                SignUpOrWithText()

                ButtonGoogleSignUp(onClick = {

                })

                GoToLoginText(onLoginClicked = onLoginClicked)
            }
        }

        if (registerViewModel.registerResult.value.isSuccessful)
            onRegister()
    }
}

