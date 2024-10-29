package com.felipeschoffen.onbudget.ui.navigation.onboarding

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.ui.navigation.main.Screens
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.LoginScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.login.LoginViewModel
import com.felipeschoffen.onbudget.ui.onboarding.welcome.WelcomeScreen

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavController,
    registrationStep: RegistrationStep?
) {
    navigation<Screens.OnBoarding>(
        startDestination = when(registrationStep) {
            null -> Screens.OnBoarding.Welcome
            else -> Screens.OnBoarding.Register
        }
    ) {
        composable<Screens.OnBoarding.Welcome> {
            WelcomeScreen(
                onLoginClicked = {
                    navController.navigate(Screens.OnBoarding.Login)
                },
                onRegisterClicked = {
                    navController.navigate(Screens.OnBoarding.Register)
                }
            )
        }

        composable<Screens.OnBoarding.Login> {
            LoginScreen(
                navController = navController,
                loginViewModel = hiltViewModel<LoginViewModel>()
            )
        }

        registerNavGraph(navController = navController, registrationStep = registrationStep)

        forgotPasswordNavGraph(navController = navController)
    }
}