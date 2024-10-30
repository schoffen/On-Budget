package com.felipeschoffen.onbudget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.data.model.FirebaseUser
import com.felipeschoffen.onbudget.ui.navigation.main.Screens
import com.felipeschoffen.onbudget.ui.navigation.main.mainNavGraph
import com.felipeschoffen.onbudget.ui.navigation.onboarding.onBoardingNavGraph

@Composable
fun RootNavGraph(userInformation: FirebaseUser?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = when (userInformation?.registrationStep) {
            RegistrationStep.COMPLETE -> Screens.Main
            else -> Screens.OnBoarding
        },
        builder = {
            onBoardingNavGraph(
                navController = navController,
                registrationStep = userInformation?.registrationStep
            )

            mainNavGraph(navController = navController)
        }
    )
}