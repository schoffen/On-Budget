package com.felipeschoffen.montrabudgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.felipeschoffen.montrabudgetapp.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.montrabudgetapp.ui.onboarding.setup.AllSetScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                AllSetScreen()
            }
        }
    }
}