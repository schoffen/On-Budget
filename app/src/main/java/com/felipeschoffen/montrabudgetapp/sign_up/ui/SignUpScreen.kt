package com.felipeschoffen.montrabudgetapp.sign_up.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        topBar = {
            SignUpTopAppBar()
        }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            SignUpInputs()
        }
    }
}

