package com.felipeschoffen.onbudget.ui.core.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
fun CustomTextFieldPlaceholder(text: String) = Text(text = text, style = MaterialTheme.typography.labelSmall)

@Composable
fun Modifier.customOutlinedTextFieldModifier() = this.fillMaxWidth().heightIn(56.dp)

@Composable
fun customOutlinedTextFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = MaterialTheme.colorScheme.primary,
    unfocusedTextColor = MaterialTheme.colorScheme.outline,
    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
    unfocusedContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent
)

@Composable
fun customOutlinedTextFieldShape() = RoundedCornerShape(16.dp)

@Composable
fun customTextStyle() = MaterialTheme.typography.bodyMedium

@Composable
fun customEmailKeyboardOptions() = KeyboardOptions(keyboardType = KeyboardType.Email)

@Composable
fun customPasswordKeyboardOptions() = KeyboardOptions(keyboardType = KeyboardType.Password)

@Composable
fun CustomPasswordVisibilityOffTrailingIcon() = Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)

@Composable
fun CustomPasswordVisibilityOnTrailingIcon() = Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)