package com.felipeschoffen.montrabudgetapp.pin.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PinInputTitle(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = Color.White
    )
}