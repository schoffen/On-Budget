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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.onbudget.ui.core.inputs.EmailOutlinedTextField
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.components.ForgotPasswordTopAppBar

@Composable
fun ForgotPasswordScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier,
        topBar = {
            ForgotPasswordTopAppBar(onBackPressed = onBackPressed)
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

                var email by remember { mutableStateOf("") }

                EmailOutlinedTextField(
                    onValueChanged = {
                        email = it
                    },
                    value = email,
                )

                CustomButtonPrimary(
                    onClick = {

                    },
                    text = stringResource(R.string.action_continue)
                )
            }
        }
    }
}