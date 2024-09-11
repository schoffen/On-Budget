package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
internal fun CustomTextFieldPlaceholder(text: String) = Text(text = text, style = MaterialTheme.typography.labelSmall)

@Composable
internal fun Modifier.customOutlinedTextFieldModifier() = this.fillMaxWidth().height(56.dp)

@Composable
internal fun customOutlinedTextFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.primary,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainer,
    unfocusedContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent
)

@Composable
internal fun customOutlinedTextFieldShape() = RoundedCornerShape(16.dp)

@Composable
internal fun customTextStyle() = MaterialTheme.typography.bodyMedium

@Composable
internal fun customEmailKeyboardOptions() = KeyboardOptions(keyboardType = KeyboardType.Email)

@Composable
internal fun customPasswordKeyboardOptions() = KeyboardOptions(keyboardType = KeyboardType.Password)

@Composable
internal fun CustomPasswordVisibilityOffTrailingIcon() = Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)

@Composable
internal fun CustomPasswordVisibilityOnTrailingIcon() = Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)