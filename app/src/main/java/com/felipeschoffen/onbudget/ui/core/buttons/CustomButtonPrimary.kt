package com.felipeschoffen.onbudget.ui.core.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomButtonPrimary(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    showLoadingProgress: Boolean = false
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.customButtonModifier(),
        shape = customButtonShape(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        if (showLoadingProgress)
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
        else
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
    }
}