package com.felipeschoffen.montrabudgetapp.ui.onboarding.verification.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.domain.util.ErrorMessages
import com.felipeschoffen.montrabudgetapp.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.montrabudgetapp.ui.core.inputs.OTPField
import com.felipeschoffen.montrabudgetapp.ui.navigation.Screens
import com.felipeschoffen.montrabudgetapp.verification.ui.VerificationTopAppBar

@Composable
fun VerificationCodeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            VerificationTopAppBar()
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.verification_code_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                OTPField()

                Text(
                    text = "05:00",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )

                val emailText = "fschoffen@gmail.com"

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            append(stringResource(R.string.verification_email_sent))
                            append(" ")
                        }

                        withStyle(
                            SpanStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append(emailText)
                        }

                        withStyle(
                            SpanStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            append(stringResource(R.string.verification_check_inbox))
                        }
                    },
                    textAlign = TextAlign.Start
                )

                Text(
                    text = stringResource(R.string.verification_resend_code),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Start
                )

                CustomButtonPrimary(
                    onClick = {

                    },
                    text = stringResource(R.string.action_verify)
                )
            }
        }
    }
}

@Composable
fun VerificationEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    verificationViewModel: VerificationViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        verificationViewModel.verificationEvents.collect { event ->
            when (event) {
                is VerificationEvents.ShowMessage -> snackbarHostState.showSnackbar(event.message)
                VerificationEvents.VerificationSuccessful -> navController.navigate(Screens.Home)
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.verification_email_screen_title),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomButtonPrimary(
                onClick = {
                    verificationViewModel.onContinueClicked()
                },
                text = stringResource(R.string.action_continue)
            )
        }
    }
}