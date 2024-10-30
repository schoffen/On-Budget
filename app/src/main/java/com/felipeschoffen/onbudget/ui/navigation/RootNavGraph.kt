package com.felipeschoffen.onbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.felipeschoffen.onbudget.ui.home.HomeScreen
import com.felipeschoffen.onbudget.ui.navigation.home.Screens
import com.felipeschoffen.onbudget.ui.navigation.onboarding.onBoardingNavGraph
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinInputScreen
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinViewModel

@Composable
fun RootNavGraph(userInformation: FirebaseUser?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = when (userInformation?.registrationStep) {
            RegistrationStep.COMPLETE -> Screens.Pin
            else -> Screens.OnBoarding
        },
        builder = {
            onBoardingNavGraph(
                navController = navController,
                registrationStep = userInformation?.registrationStep
            )

            composable<Screens.Pin> {
                PinInputScreen(
                    navController = navController,
                    pinViewModel = hiltViewModel<PinViewModel>()
                )
            }

            composable<Screens.Home> {
                HomeScreen()
            }
        }
    )
}