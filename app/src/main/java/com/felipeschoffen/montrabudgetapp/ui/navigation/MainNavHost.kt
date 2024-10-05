package com.felipeschoffen.montrabudgetapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.felipeschoffen.montrabudgetapp.ui.home.HomeScreen
import com.felipeschoffen.montrabudgetapp.ui.onboarding.verification.ui.VerificationScreen
import com.google.firebase.auth.FirebaseUser

@Composable
fun MainNavHost(
    navController: NavHostController,
    user: FirebaseUser?
) {
    NavHost(
        navController = navController,
        startDestination = if (user == null) Screens.OnBoarding else if (!user.isEmailVerified) Screens.OnBoarding.Auth.Register.Verification else Screens.Home,
        builder = {
            onBoardingNavGraph(navController)

            composable<Screens.Home> { HomeScreen() }

            composable<Screens.OnBoarding.Auth.Register.Verification> {
                VerificationScreen()
            }
        }
    )
}

