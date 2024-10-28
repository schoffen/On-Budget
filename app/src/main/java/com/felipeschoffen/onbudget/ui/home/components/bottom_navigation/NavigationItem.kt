package com.felipeschoffen.onbudget.ui.home.components.bottom_navigation

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
import com.felipeschoffen.onbudget.ui.navigation.Screens

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
            Screens.Home
    )

    data object Transactions : NavigationItem(
        R.string.transactions,
        Icons.Filled.Sync,
        Icons.Outlined.Sync,
        Screens.Home.Transactions
    )

    data object Budgets : NavigationItem(
        R.string.budget,
        Icons.Filled.Savings,
        Icons.Outlined.Savings,
        Screens.Home.Budgets
    )

    data object Profile : NavigationItem(
        R.string.profile,
        Icons.Filled.Person,
        Icons.Outlined.Person,
        Screens.Home.Profile
    )
}

val bottomNavigationItems = listOf(
    NavigationItem.Home,
    NavigationItem.Transactions,
    NavigationItem.Budgets,
    NavigationItem.Profile
)