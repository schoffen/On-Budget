package com.felipeschoffen.onbudget.ui.onboarding.auth.recovery

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.onbudget.ui.core.inputs.EmailOutlinedTextField
import com.felipeschoffen.onbudget.ui.navigation.main.Screens
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.components.ForgotPasswordTopAppBar

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    forgotPasswordViewModel: ForgotPasswordViewModel,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by forgotPasswordViewModel.uiState

    LaunchedEffect(Unit) {
        forgotPasswordViewModel.uiEvent.collect { event ->
            when (event) {
                ForgotPasswordEvent.Continue -> navController.navigate(
                    Screens.OnBoarding.ForgotPassword.EmailSent(
                        uiState.email
                    )
                )

                is ForgotPasswordEvent.ShowError -> snackbarHostState.showSnackbar(message = event.message)
            }
        }
    }

    Scaffold(modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            ForgotPasswordTopAppBar(onBackPressed = {
                navController.navigateUp()
            })
        }) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .imePadding()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password_instructions),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                EmailOutlinedTextField(
                    onValueChanged = {
                        forgotPasswordViewModel.onAction(ForgotPasswordAction.OnEmailChange(it))
                    },
                    value = uiState.email,
                    isError = !uiState.isEmailValid,
                    errorMessage = uiState.emailErrorMessage
                )

                CustomButtonPrimary(
                    onClick = {
                        forgotPasswordViewModel.onAction(ForgotPasswordAction.Continue)
                    },
                    text = stringResource(R.string.action_continue),
                    showLoadingProgress = uiState.isLoading
                )
            }
        }
    }
}