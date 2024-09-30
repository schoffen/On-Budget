package com.felipeschoffen.montrabudgetapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.*
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.montrabudgetapp.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.montrabudgetapp.ui.navigation.MainNavHost
import com.felipeschoffen.montrabudgetapp.ui.onboarding.setup.AllSetScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                val navController = rememberNavController()

                MainNavHost(navController = navController, isUserLoggedIn = true)
            }
        }
    }
}