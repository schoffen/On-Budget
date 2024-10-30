package com.felipeschoffen.onbudget.ui.navigation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.felipeschoffen.onbudget.ui.home.budgets_content.BudgetsContent
import com.felipeschoffen.onbudget.ui.home.home_content.HomeContent
import com.felipeschoffen.onbudget.ui.home.profile_content.ProfileContent
import com.felipeschoffen.onbudget.ui.home.transactions_content.TransactionsContent

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            HomeContent()
        }

        composable<Screens.Home.Transactions> {
            TransactionsContent()
        }

        composable<Screens.Home.Budgets> {
            BudgetsContent()
        }

        composable<Screens.Home.Profile> {
            ProfileContent()
        }
    }
}