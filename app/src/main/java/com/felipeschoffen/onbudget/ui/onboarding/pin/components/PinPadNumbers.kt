package com.felipeschoffen.onbudget.ui.onboarding.pin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PinNumberPad(
    pin: String,
    onPinChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Column {
        OneToNineNumbers(pin, onPinChange)
        BackspaceZeroSend(pin, onPinChange, onSend)
    }
}

@Composable
private fun BackspaceZeroSend(
    pin: String,
    onPinChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BackspaceButton(pin, onPinChange, modifier = Modifier.weight(1f))
        ZeroButton(pin, onPinChange, modifier = Modifier.weight(1f))
        SendButton(onSend, pin, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ZeroButton(pin: String, onPinChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        onClick = {
            if (pin.length < 4) {
                onPinChange(pin + 0)
            }
        },
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = "0",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

@Composable
private fun SendButton(onSend: () -> Unit, pin: String, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onSend,
        modifier = modifier
            .padding(8.dp),
        enabled = pin.length == 4
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun BackspaceButton(pin: String, onPinChange: (String) -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            if (pin.isNotEmpty()) {
                onPinChange(pin.dropLast(1))
            }
        },
        modifier = modifier
            .padding(8.dp),
        enabled = pin.isNotEmpty()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Backspace,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun OneToNineNumbers(pin: String, onPinChange: (String) -> Unit) {
    for (row in 0..2) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (col in 1..3) {
                val number = (row * 3) + col
                TextButton(
                    onClick = {
                        if (pin.length < 4) {
                            onPinChange(pin + number)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = number.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}