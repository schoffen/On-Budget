package com.felipeschoffen.onbudget.ui.onboarding.verification.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.onbudget.ui.navigation.home.Screens
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun VerificationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    verificationViewModel: VerificationViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        verificationViewModel.events.collect { event ->
            when (event) {
                is VerificationEvents.ShowMessage -> snackbarHostState.showSnackbar(event.message)
                is VerificationEvents.VerificationSuccessful -> navController.navigate(Screens.OnBoarding.Register.SetupPin)
            }
        }
    }

    Scaffold(
        topBar = {
            VerificationTopAppBar(onBackPressed = {
                verificationViewModel.signOut()
                navController.navigateUp()
            })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.email_on_the_way),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(240.dp)
                )

                Text(
                    text = stringResource(R.string.verification_subtitle),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // For future usage
                // OTPField()

                val timeBetweenRequests = 120
                var timeLeft by remember { mutableIntStateOf(timeBetweenRequests) }
                var timeRunning by remember { mutableStateOf(true) }

                LaunchedEffect(timeRunning) {
                    while (timeLeft > 0) {
                        delay(1000L)
                        timeLeft--
                    }
                    timeRunning = false
                }

                Text(
                    text = String.format(Locale.US, "%02d:%02d", (timeLeft / 60), (timeLeft % 60)),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )

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
                            append(verificationViewModel.userEmail)
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
                    textAlign = TextAlign.Start,
                    modifier = Modifier.clickable {
                        if (!timeRunning) {
                            verificationViewModel.resendEmailVerification()
                            timeLeft = timeBetweenRequests
                            timeRunning = true
                        }
                    }
                )
            }
            CustomButtonPrimary(
                onClick = {
                    verificationViewModel.onContinueClicked()
                },
                text = stringResource(R.string.action_continue),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}