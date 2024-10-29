package com.felipeschoffen.onbudget.ui.navigation.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.felipeschoffen.onbudget.ui.home.HomeScreen
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinInputScreen
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinViewModel

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation<Screens.Main>(
        startDestination = Screens.Main.Pin
    ) {
        composable<Screens.Main.Pin> {
            PinInputScreen(
                navController = navController,
                pinViewModel = hiltViewModel<PinViewModel>()
            )
        }

        composable<Screens.Main.Home> {
            HomeScreen()
        }
    }
}