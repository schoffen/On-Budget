package com.felipeschoffen.onbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felipeschoffen.onbudget.core.RegistrationStep
import com.felipeschoffen.onbudget.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.onbudget.ui.home.HomeScreen
import com.felipeschoffen.onbudget.ui.navigation.Screens
import com.felipeschoffen.onbudget.ui.navigation.onBoardingNavGraph
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinInputScreen
import com.felipeschoffen.onbudget.ui.onboarding.pin.PinViewModel
import com.felipeschoffen.onbudget.ui.financial_account.IntroductionAccountScreen
import com.felipeschoffen.onbudget.ui.navigation.createFinancialAccountNavGraph
import com.felipeschoffen.onbudget.ui.onboarding.verification.ui.VerificationScreen
import com.felipeschoffen.onbudget.ui.onboarding.verification.ui.VerificationViewModel
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

                if (mainViewModel.mainUIState.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = when (mainViewModel.userInformation?.registrationStep) {
                            RegistrationStep.VERIFICATION -> Screens.OnBoarding.Auth.Register.Verification
                            RegistrationStep.SETUP_PIN -> Screens.OnBoarding.Auth.Register.PIN
                            RegistrationStep.SETUP_FINANCIAL_ACCOUNT -> Screens.OnBoarding.CreateFinancialAccount
                            RegistrationStep.COMPLETE -> Screens.OnBoarding.Auth.Register.PIN
                            null -> Screens.OnBoarding
                        },
                        builder = {
                            onBoardingNavGraph(navController)

                            createFinancialAccountNavGraph(navController)

                            composable<Screens.Home> { HomeScreen() }

                            composable<Screens.OnBoarding.Auth.Register.Verification> {
                                VerificationScreen(
                                    navController = navController,
                                    verificationViewModel = hiltViewModel<VerificationViewModel>()
                                )
                            }

                            composable<Screens.OnBoarding.Auth.Register.PIN> {
                                PinInputScreen(
                                    navController = navController,
                                    pinViewModel = hiltViewModel<PinViewModel>()
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}