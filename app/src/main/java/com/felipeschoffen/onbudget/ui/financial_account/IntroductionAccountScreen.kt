package com.felipeschoffen.onbudget.ui.financial_account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.onbudget.ui.navigation.home.Screens

@Composable
fun IntroductionAccountScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
                .padding(top = 64.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    text = stringResource(R.string.setup_account_title),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(R.string.setup_account_description),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            CustomButtonPrimary(
                onClick = { navController.navigate(Screens.OnBoarding.Register.SetupFinancialAccount.Create)},
                text = stringResource(R.string.action_setup_account)
            )
        }
    }
}