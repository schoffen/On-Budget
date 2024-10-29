package com.felipeschoffen.onbudget.ui.navigation.onboarding

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.felipeschoffen.onbudget.ui.financial_account.AllSetScreen
import com.felipeschoffen.onbudget.ui.financial_account.CreateAccountScreen
import com.felipeschoffen.onbudget.ui.financial_account.CreateAccountViewModel
import com.felipeschoffen.onbudget.ui.financial_account.IntroductionAccountScreen
import com.felipeschoffen.onbudget.ui.navigation.main.Screens

fun NavGraphBuilder.setupFinancialAccountNavGraph(navController: NavController) {
    navigation<Screens.OnBoarding.Register.SetupFinancialAccount>(
        startDestination = Screens.OnBoarding.Register.SetupFinancialAccount.Introduction
    ) {
        composable<Screens.OnBoarding.Register.SetupFinancialAccount.Introduction> {
            IntroductionAccountScreen(navController = navController)
        }

        composable<Screens.OnBoarding.Register.SetupFinancialAccount.Create> {
            CreateAccountScreen(
                createAccountViewModel = hiltViewModel<CreateAccountViewModel>(),
                navController = navController
            )
        }

        composable<Screens.OnBoarding.Register.SetupFinancialAccount.AllSet> {
            AllSetScreen(navController = navController)
        }
    }
}