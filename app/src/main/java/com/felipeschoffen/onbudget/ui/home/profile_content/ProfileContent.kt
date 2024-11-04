package com.felipeschoffen.onbudget.ui.home.profile_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.theme.MontraBudgetAppTheme
import com.felipeschoffen.onbudget.ui.home.profile_content.components.MenuItem
import com.felipeschoffen.onbudget.ui.home.profile_content.components.ProfileInfo
import com.felipeschoffen.onbudget.ui.home.profile_content.components.ProfileMenu

@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        val primaryColorBackground = MaterialTheme.colorScheme.primary.copy(alpha = .2f)

        val items = listOf(
            MenuItem(
                stringResource(R.string.accounts),
                Icons.Filled.Wallet,
                primaryColorBackground,
                iconTint = MaterialTheme.colorScheme.primary
            ),
            MenuItem(
                stringResource(R.string.settings),
                Icons.Filled.Settings,
                primaryColorBackground,
                iconTint = MaterialTheme.colorScheme.primary
            ),
            MenuItem(
                stringResource(R.string.logout),
                Icons.AutoMirrored.Filled.Logout,
                MaterialTheme.colorScheme.error.copy(.2f),
                iconTint = MaterialTheme.colorScheme.error
            )
        )

        ProfileInfo()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileMenu(items)
    }
}

@Preview
@Composable
fun ProfileContentPreview(modifier: Modifier = Modifier) {
    MontraBudgetAppTheme { ProfileContent(modifier = Modifier.background(Color.White)) }
}