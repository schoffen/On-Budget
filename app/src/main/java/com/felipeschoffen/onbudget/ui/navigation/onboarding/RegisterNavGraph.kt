package com.felipeschoffen.onbudget.ui.navigation.onboarding

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.ui.navigation.main.Screens
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.RegisterScreen
import com.felipeschoffen.onbudget.ui.onboarding.auth.register.RegisterViewModel
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinInputScreen
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinViewModel
import com.felipeschoffen.onbudget.ui.onboarding.verification.ui.VerificationScreen
import com.felipeschoffen.onbudget.ui.onboarding.verification.ui.VerificationViewModel

fun NavGraphBuilder.registerNavGraph(
    navController: NavController,
    registrationStep: RegistrationStep?
) {
    navigation<Screens.OnBoarding.Register>(
        startDestination = when(registrationStep) {
            RegistrationStep.VERIFICATION -> Screens.OnBoarding.Register.Verification
            RegistrationStep.SETUP_PIN -> Screens.OnBoarding.Register.SetupPin
            RegistrationStep.SETUP_FINANCIAL_ACCOUNT -> Screens.OnBoarding.Register.SetupFinancialAccount
            else -> Screens.OnBoarding.Register.CreateUser
        }
    ) {
        composable<Screens.OnBoarding.Register.CreateUser> {
            RegisterScreen(
                navController = navController,
                registerViewModel = hiltViewModel<RegisterViewModel>(),
                onBackPressed = {
                    navController.navigateUp()
                },
                onLoginClicked = {
                    navController.popBackStack()
                    navController.navigate(Screens.OnBoarding.Login)
                }
            )
        }

        composable<Screens.OnBoarding.Register.Verification> {
            VerificationScreen(
                navController = navController,
                verificationViewModel = hiltViewModel<VerificationViewModel>()
            )
        }

        composable<Screens.OnBoarding.Register.SetupPin> {
            PinInputScreen(
                navController = navController,
                pinViewModel = hiltViewModel<PinViewModel>()
            )
        }

        setupFinancialAccountNavGraph(navController)
    }
}