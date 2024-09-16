package com.felipeschoffen.montrabudgetapp.verification.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.buttons.CustomButtonPrimary
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.OTPField

@Composable
fun VerificationScreen(
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
                    text = stringResource(R.string.enter_verification_code),
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
                            append(stringResource(R.string.email_send_message_pt1))
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
                            append(stringResource(R.string.email_send_message_pt2))
                        }
                    },
                    textAlign = TextAlign.Start
                )

                Text(
                    text = stringResource(R.string.didnt_recieve_code),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Start
                )

                CustomButtonPrimary(
                    onClick = {

                    },
                    text = stringResource(R.string.verify)
                )
            }
        }
    }
}