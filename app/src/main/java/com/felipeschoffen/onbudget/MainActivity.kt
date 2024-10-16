package com.felipeschoffen.onbudget

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.onbudget.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.onbudget.ui.home.HomeScreen
import com.felipeschoffen.onbudget.ui.navigation.Screens
import com.felipeschoffen.onbudget.ui.navigation.onBoardingNavGraph
import com.felipeschoffen.onbudget.ui.onboarding.verification.ui.VerificationEmailScreen
import com.felipeschoffen.onbudget.ui.onboarding.verification.ui.VerificationViewModel
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
                Log.d("currentUser", currentUser?.isEmailVerified.toString())

                NavHost(
                    navController = navController,
                    startDestination = if (currentUser == null) Screens.OnBoarding
                    else if (!currentUser.isEmailVerified) Screens.OnBoarding.Auth.Register.Verification
                    else Screens.Home,
                    builder = {
                        onBoardingNavGraph(navController)

                        composable<Screens.Home> { HomeScreen() }

                        composable<Screens.OnBoarding.Auth.Register.Verification> {
                            VerificationEmailScreen(
                                navController = navController,
                                verificationViewModel = hiltViewModel<VerificationViewModel>()
                            )
                        }
                    }
                )
            }
        }
    }
}