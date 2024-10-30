package com.felipeschoffen.onbudget.ui.onboarding.auth.register

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.onbudget.ui.core.buttons.ButtonGoogleSignUp
import com.felipeschoffen.onbudget.ui.navigation.home.Screens
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.components.GoToLoginText
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.components.RegisterTopAppBar
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.components.SignUpButton
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.components.SignUpInputs
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.components.SignUpOrWithText
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.components.SignUpPrivacyAgreement

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onBackPressed: () -> Unit,
    onLoginClicked: () -> Unit,
    registerViewModel: RegisterViewModel
) {
    val registerFormState by registerViewModel.registerFormState
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        registerViewModel.events.collect { event ->

            when (event) {
                is RegisterEvents.RegisterSuccessful -> {
                    navController.popBackStack()
                    navController.navigate(Screens.OnBoarding.Register.Verification)
                }

                is RegisterEvents.ShowMessage -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            RegisterTopAppBar(onBackPressed = onBackPressed)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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

                SignUpPrivacyAgreement(
                    checked = registerFormState.isTermsChecked,
                    onCheckedChange = { registerViewModel.onTermsChecked() })

                SignUpButton(onClick = {
                    keyboardController?.hide()
                    registerViewModel.registerWithEmail()
                }, isLoading = registerViewModel.requestLoading)

                SignUpOrWithText()

                ButtonGoogleSignUp(onClick = {

                })

                GoToLoginText(onLoginClicked = onLoginClicked)
            }
        }
    }
}

