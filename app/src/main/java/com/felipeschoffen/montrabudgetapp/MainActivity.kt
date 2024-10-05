package com.felipeschoffen.montrabudgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.montrabudgetapp.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.montrabudgetapp.ui.navigation.MainNavHost
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                val navController = rememberNavController()

                val currentUser = firebaseAuth.currentUser

                MainNavHost(navController = navController, user = currentUser)
            }
        }
    }
}