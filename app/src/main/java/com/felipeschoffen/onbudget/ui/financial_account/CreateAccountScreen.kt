package com.felipeschoffen.onbudget.ui.financial_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.data.model.AccountType
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.onbudget.ui.core.inputs.DropDownMenuItem
import com.felipeschoffen.onbudget.ui.core.inputs.DropDownSelector
import com.felipeschoffen.onbudget.ui.core.inputs.LabeledOutlinedTextField
import com.felipeschoffen.onbudget.ui.financial_account.components.BalanceInput
import com.felipeschoffen.onbudget.ui.navigation.main.Screens

@Composable
fun CreateAccountScreen(
    modifier: Modifier = Modifier,
    createAccountViewModel: CreateAccountViewModel,
    navController: NavController
) {

    val uiState by createAccountViewModel.uiState
    val snackbarHostState = SnackbarHostState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        createAccountViewModel.events.collect { event ->
            when (event) {
                is CreateAccountEvents.CreateSuccessful -> navController.navigate(Screens.OnBoarding.Register.SetupFinancialAccount.AllSet)
                is CreateAccountEvents.ShowMessage -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        bottomBar = {
            Box(modifier = Modifier.background(color = Color.White))
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.balance),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                BalanceInput(
                    balance = uiState.account.balance,
                    onBalanceChanged = { createAccountViewModel.onBalanceChanged(it) })
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 32.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                LabeledOutlinedTextField(
                    onValueChanged = {
                        createAccountViewModel.onNameChanged(it)
                    },
                    value = uiState.account.name,
                    placeholder = stringResource(R.string.name_hint),
                    isError = !uiState.isNameValid,
                    errorMessage = uiState.nameErrorMessage
                )

                var expanded by remember { mutableStateOf(false) }

                DropDownSelector(
                    value = uiState.account.type.name,
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = it
                    },
                    onDismissRequest = {
                        expanded = !expanded
                    },
                    items = listOf(
                        DropDownMenuItem(name = AccountType.Wallet.name, onItemClicked = {
                            createAccountViewModel.onTypeChanged(AccountType.Wallet)
                        }),
                        DropDownMenuItem(name = AccountType.Bank.name, onItemClicked = {
                            createAccountViewModel.onTypeChanged(AccountType.Bank)
                        })
                    )
                )

                CustomButtonPrimary(
                    onClick = {
                        keyboardController?.hide()
                        createAccountViewModel.onContinueClicked()
                    },
                    text = stringResource(R.string.action_continue),
                    showLoadingProgress = uiState.isLoading
                )
            }
        }
    }
}