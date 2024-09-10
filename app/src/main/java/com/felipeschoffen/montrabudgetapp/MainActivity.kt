package com.felipeschoffen.montrabudgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.felipeschoffen.montrabudgetapp.core.ui.theme.MontraBudgetAppTheme
import com.felipeschoffen.montrabudgetapp.login.ui.LoginScreen
import com.felipeschoffen.montrabudgetapp.onboarding.ui.OnBoardingScreen
import com.felipeschoffen.montrabudgetapp.sign_up.ui.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                SignUpScreen()
            }
        }
    }
}