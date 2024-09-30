package com.felipeschoffen.montrabudgetapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavHost(
    navController: NavHostController,
    isUserLoggedIn: Boolean
) {
    val startDestination = remember {
        // TODO: check if use is logged in (not implemented yet)
        Screens.OnBoarding
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            onBoardingNavGraph(navController)
        }
    )
}

