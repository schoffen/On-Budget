package com.felipeschoffen.onbudget.ui.onboarding.auth.login

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
import com.felipeschoffen.onbudget.ui.navigation.Screens
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.components.ForgotPasswordTextButton
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.components.GoToRegisterText
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.components.LoginButton
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.components.LoginInputs
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.components.LoginTopAppBar

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val loginFormState by loginViewModel.loginFormState
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        loginViewModel.loginEvents.collect { event ->
            when (event) {
                is LoginEvents.LoginSuccessful -> {
                    if (!event.isEmailVerified)
                        navController.navigate(Screens.OnBoarding.Auth.Register.Verification)
                    else
                        navController.navigate(Screens.Home)
                }

                is LoginEvents.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(modifier = Modifier,
        topBar = {
            LoginTopAppBar(onBackPressed = {
                navController.navigateUp()
            })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
                LoginInputs(
                    loginFormState = loginFormState,
                    onEmailChange = { loginViewModel.onEmailChange(it) },
                    onPasswordChange = { loginViewModel.onPasswordChange(it) }
                )

                LoginButton(
                    onClick = {
                        keyboardController?.hide()
                        loginViewModel.login()
                    },
                    isLoading = loginViewModel.isLoading
                )

                ForgotPasswordTextButton(onClick = {
                    navController.navigate(Screens.OnBoarding.Auth.ForgotPassword)
                })

                GoToRegisterText(
                    onSignUpClicked = {
                        navController.popBackStack()
                        navController.navigate(Screens.OnBoarding.Auth.Register)
                    }
                )
            }
        }
    }
}

