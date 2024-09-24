package com.felipeschoffen.montrabudgetapp.add_account.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.model.Account
import com.felipeschoffen.montrabudgetapp.core.model.AccountType
import com.felipeschoffen.montrabudgetapp.core.ui.buttons.CustomButtonPrimary
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.DropDownMenuItem
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.DropDownSelector
import com.felipeschoffen.montrabudgetapp.core.ui.inputs.LabeledOutlinedTextField

@Composable
fun AddAccountScreen(modifier: Modifier = Modifier) {

    var accountName by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        bottomBar = {
            Box(modifier = Modifier.background(color = Color.White))
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.fillMaxSize().imePadding(),
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
                ValueInput()
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
                        accountName = it
                    },
                    value = accountName,
                    placeholder = stringResource(R.string.name_hint)
                )

                var value by remember { mutableStateOf("") }
                var expanded by remember { mutableStateOf(false) }

                DropDownSelector(
                    value = value,
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = it
                    },
                    onDismissRequest = {
                        expanded = !expanded
                    },
                    items = listOf(
                        DropDownMenuItem(name = "Wallet", onItemClicked = {
                            value = AccountType.Wallet.typeName
                        }),
                        DropDownMenuItem(name = "Bank", onItemClicked = {
                            value = AccountType.Bank.typeName
                        })
                    )
                )

                CustomButtonPrimary(
                    onClick = {},
                    text = stringResource(R.string.action_continue)
                )
            }
        }
    }
}