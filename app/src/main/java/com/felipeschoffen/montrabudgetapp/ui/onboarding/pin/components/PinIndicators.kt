package com.felipeschoffen.montrabudgetapp.ui.onboarding.pin.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PinIndicators(pin: String) {
    Row {
        repeat(4) { index ->
            val icon =
                if (index < pin.length) Icons.Filled.Circle else Icons.Outlined.Circle
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            if (index < 3) Spacer(modifier = Modifier.width(16.dp))
        }
    }
}