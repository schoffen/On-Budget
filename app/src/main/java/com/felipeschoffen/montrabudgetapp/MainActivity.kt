package com.felipeschoffen.montrabudgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.felipeschoffen.montrabudgetapp.core.ui.theme.MontraBudgetAppTheme
import com.felipeschoffen.montrabudgetapp.login.ui.LoginScreen
import com.felipeschoffen.montrabudgetapp.verification.ui.VerificationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                LoginScreen()
            }
        }
    }
}