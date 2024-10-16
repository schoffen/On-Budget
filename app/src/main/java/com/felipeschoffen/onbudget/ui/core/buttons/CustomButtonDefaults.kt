package com.felipeschoffen.onbudget.ui.core.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.customButtonModifier() = this.fillMaxWidth().height(56.dp)

@Composable
fun customButtonShape() = RoundedCornerShape(16.dp)