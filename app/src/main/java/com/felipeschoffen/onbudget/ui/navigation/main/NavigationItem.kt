package com.felipeschoffen.onbudget.ui.navigation.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.ui.graphics.vector.ImageVector
import com.felipeschoffen.onbudget.R

sealed class NavigationItem(
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Any
) {
    data object Home : NavigationItem(
            R.string.home,
            Icons.Filled.Home,
            Icons.Outlined.Home,
        Screens.Main
    )

    data object Transactions : NavigationItem(
        R.string.transactions,
        Icons.Filled.Sync,
        Icons.Outlined.Sync,
        Screens.Main.Transactions
    )

    data object Budgets : NavigationItem(
        R.string.budget,
        Icons.Filled.Savings,
        Icons.Outlined.Savings,
        Screens.Main.Budgets
    )

    data object Profile : NavigationItem(
        R.string.profile,
        Icons.Filled.Person,
        Icons.Outlined.Person,
        Screens.Main.Profile
    )
}

val bottomNavigationItems = listOf(
    NavigationItem.Home,
    NavigationItem.Transactions,
    NavigationItem.Budgets,
    NavigationItem.Profile
)