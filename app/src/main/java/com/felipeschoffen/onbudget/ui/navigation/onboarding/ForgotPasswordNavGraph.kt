package com.felipeschoffen.onbudget.ui.navigation.onboarding

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.felipeschoffen.onbudget.ui.navigation.main.Screens
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.EmailSentScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.ForgotPasswordScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.ForgotPasswordViewModel

fun NavGraphBuilder.forgotPasswordNavGraph(
    navController: NavController
) {
    navigation<Screens.OnBoarding.ForgotPassword>(
        startDestination = Screens.OnBoarding.ForgotPassword.Request
    ) {
        composable<Screens.OnBoarding.ForgotPassword.Request> {
            ForgotPasswordScreen(
                navController = navController,
                forgotPasswordViewModel = hiltViewModel<ForgotPasswordViewModel>()
            )
        }

        composable<Screens.OnBoarding.ForgotPassword.EmailSent> { backStackEntry ->
            val args =
                requireNotNull(backStackEntry.toRoute<Screens.OnBoarding.ForgotPassword.EmailSent>())
            EmailSentScreen(
                navController = navController,
                email = args.email
            )
        }
    }
}