package com.felipeschoffen.montrabudgetapp.login.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier,
        topBar = {
            LoginTopAppBar()
        }) {

    }
}

