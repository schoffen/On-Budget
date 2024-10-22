package com.felipeschoffen.onbudget.ui.onboarding.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.navigation.Screens
import com.felipeschoffen.onbudget.ui.onboarding.pin.components.PinIndicators
import com.felipeschoffen.onbudget.ui.onboarding.pin.components.PinInputTitle
import com.felipeschoffen.onbudget.ui.onboarding.pin.components.PinNumberPad

@Composable
fun PinInputScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    pinViewModel: PinViewModel
) {
    val pinUIState by pinViewModel.pinUIState
    val inputValue by pinViewModel.inputValue
    val step by pinViewModel.step
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        pinViewModel.pinEvents.collect { event ->
            when (event) {
                is PinEvents.CheckFailed -> snackbarHostState.showSnackbar(message = "Authentication Failed")
                is PinEvents.ShowMessage -> snackbarHostState.showSnackbar(message = event.message)
                is PinEvents.SuccessfullyChecked -> {
                    if (step == PinStep.AUTHENTICATE)
                        navController.navigate(Screens.Home)

                    if (step == PinStep.CONFIRM_NEW)
                        navController.navigate(Screens.OnBoarding.CreateFinancialAccount)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        if (pinUIState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .navigationBarsPadding()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PinInputTitle(
                            stringResource(
                                when (step) {
                                    PinStep.SETUP_NEW -> R.string.setup_new_PIN
                                    PinStep.CONFIRM_NEW -> R.string.setup_confirm_new_PIN
                                    PinStep.AUTHENTICATE -> R.string.enter_PIN
                                }
                            )
                        )
                        Spacer(modifier = Modifier.height(64.dp))
                        PinIndicators(inputValue)
                    }
                }
                Spacer(modifier = Modifier.height(64.dp))
                PinNumberPad(
                    inputValue,
                    { pinViewModel.onValueChanged(it) },
                    { pinViewModel.sendPin() })
            }
        }
    }
}

