package com.felipeschoffen.onbudget.ui.home.profile_content.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipeschoffen.onbudget.ui.core.theme.MontraBudgetAppTheme

@Composable
fun ProfileMenu(items: List<MenuItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
    ) {
        items(items) { item ->
            MenuItemRow(item)
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }
    }
}

@Composable
fun MenuItemRow(item: MenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(item.iconBackgroundColor, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = item.iconTint,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val iconBackgroundColor: Color,
    val iconTint: Color
)

@Preview
@Composable
fun PreviewProfileMenu(modifier: Modifier = Modifier) {
    val primaryColorBackground = MaterialTheme.colorScheme.primary.copy(alpha = .2f)

    val items = listOf(
        MenuItem(
            "Account",
            Icons.Filled.Wallet,
            primaryColorBackground,
            iconTint = MaterialTheme.colorScheme.primary
        ),
        MenuItem(
            "Settings",
            Icons.Filled.Settings,
            primaryColorBackground,
            iconTint = MaterialTheme.colorScheme.primary
        ),
        MenuItem(
            "Logout",
            Icons.AutoMirrored.Filled.Logout,
            MaterialTheme.colorScheme.error.copy(.2f),
            iconTint = MaterialTheme.colorScheme.error
        )
    )

    MontraBudgetAppTheme {
        ProfileMenu(items = items)
    }
}