package com.felipeschoffen.onbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.onbudget.core.util.RegistrationStep
import com.felipeschoffen.onbudget.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.onbudget.ui.navigation.main.Screens
import com.felipeschoffen.onbudget.ui.navigation.main.mainNavGraph
import com.felipeschoffen.onbudget.ui.navigation.onboarding.onBoardingNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                val navController = rememberNavController()
                mainViewModel = hiltViewModel<MainViewModel>()

                val state by mainViewModel.mainUIState
                val userInformation by mainViewModel.userInformation

                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = when (userInformation?.registrationStep) {
                            RegistrationStep.COMPLETE -> Screens.Main
                            else -> Screens.OnBoarding
                        },
                        builder = {
                            onBoardingNavGraph(
                                navController,
                                userInformation?.registrationStep
                            )

                            mainNavGraph(navController = navController)
                        }
                    )
                }
            }
        }
    }
}