package com.felipeschoffen.montrabudgetapp.sign_up.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.core.ui.buttons.ButtonGoogleSignUp

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            SignUpTopAppBar()
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center,

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SignUpInputs()

                var checked by remember { mutableStateOf(false) }
                SignUpPrivacyAgreement(checked = checked, onCheckedChange = { checked = !checked })

                SignUpButton(onClick = {

                })

                SignUpOrWithText()

                ButtonGoogleSignUp(onClick = {

                })

                SignUpAlreadyHaveAccountText({

                })
            }
        }
    }
}

