package com.felipeschoffen.onbudget.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.onbudget.ui.navigation.home.BottomNavigationBar
import com.felipeschoffen.onbudget.ui.navigation.home.HomeNavGraph

@Composable
fun HomeScreen() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar { route ->
                navController.navigate(route) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    ) { paddingValues ->
        HomeNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}