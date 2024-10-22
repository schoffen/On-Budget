package com.felipeschoffen.onbudget.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.felipeschoffen.onbudget.ui.financial_account.AllSetScreen
import com.felipeschoffen.onbudget.ui.financial_account.CreateAccountScreen
import com.felipeschoffen.onbudget.ui.financial_account.CreateAccountViewModel
import com.felipeschoffen.onbudget.ui.financial_account.IntroductionAccountScreen

fun NavGraphBuilder.createFinancialAccountNavGraph(navController: NavController) {
    navigation<Screens.OnBoarding.CreateFinancialAccount>(
        startDestination = Screens.OnBoarding.CreateFinancialAccount.Introduction
    ) {
        composable<Screens.OnBoarding.CreateFinancialAccount.Introduction> {
            IntroductionAccountScreen(navController = navController)
        }

        composable<Screens.OnBoarding.CreateFinancialAccount.Create> {
            CreateAccountScreen(
                createAccountViewModel = hiltViewModel<CreateAccountViewModel>(),
                navController = navController
            )
        }

        composable<Screens.OnBoarding.CreateFinancialAccount.AllSet> {
            AllSetScreen()
        }
    }
}