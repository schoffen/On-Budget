package com.felipeschoffen.onbudget.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.LoginScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.LoginViewModel
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.EmailSentScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.recovery.ForgotPasswordScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.RegisterScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.RegisterViewModel
import com.felipeschoffen.onbudget.ui.onboarding.welcome.WelcomeScreen

fun NavGraphBuilder.onBoardingNavGraph(navController: NavController) {
    navigation<Screens.OnBoarding>(
        startDestination = Screens.OnBoarding.Welcome
    ) {
        composable<Screens.OnBoarding.Welcome> {
            WelcomeScreen(
                onLoginClicked = {
                    navController.navigate(Screens.OnBoarding.Auth.Login)
                },
                onRegisterClicked = {
                    navController.navigate(Screens.OnBoarding.Auth.Register)
                }
            )
        }

        composable<Screens.OnBoarding.Auth.Login> {
            LoginScreen(
                navController = navController,
                loginViewModel = hiltViewModel<LoginViewModel>()
            )
        }

        composable<Screens.OnBoarding.Auth.Register> {
            RegisterScreen(
                navController = navController,
                onBackPressed = {
                    navController.navigateUp()
                },
                onLoginClicked = {
                    navController.popBackStack()
                    navController.navigate(Screens.OnBoarding.Auth.Login)
                },
                registerViewModel = hiltViewModel<RegisterViewModel>()
            )
        }

        composable<Screens.OnBoarding.Auth.ForgotPassword> {
            ForgotPasswordScreen(
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screens.OnBoarding.Auth.ForgotPassword.EmailSent> {
            val args = it.toRoute<Screens.OnBoarding.Auth.ForgotPassword.EmailSent>()
            EmailSentScreen(email = args.email)
        }
    }
}