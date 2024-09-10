package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
internal fun defaultTextFieldPlaceholder(text: String) = Text(text = text, style = MaterialTheme.typography.labelSmall)

@Composable
internal fun defaultTextFieldModifier() = Modifier
        .fillMaxWidth()
        .height(56.dp)

@Composable
internal fun defaultTextFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.primary,
    unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainer,
    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainer,
    unfocusedContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent
)

@Composable
internal fun defaultTextFieldShape() = RoundedCornerShape(16.dp)

@Composable
internal fun defaultTextStyle() = MaterialTheme.typography.bodyMedium

@Composable
internal fun defaultEmailKeyboardOptions() = KeyboardOptions(keyboardType = KeyboardType.Email)

@Composable
internal fun defaultPasswordKeyboardOptions() = KeyboardOptions(keyboardType = KeyboardType.Password)

@Composable
internal fun defaultPasswordVisibilityOffTrailingIcon() = Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)

@Composable
internal fun defaultPasswordVisibilityOnTrailingIcon() = Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)