package com.felipeschoffen.montrabudgetapp.core.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.montrabudgetapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownSelector(
    modifier: Modifier = Modifier,
    value: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    items: List<DropDownMenuItem>
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ) {
        LabeledOutlinedTextField(
            value = value,
            onValueChanged = {

            },
            placeholder = stringResource(R.string.account_type_hint),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            modifier = Modifier.background(color = Color.White)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item.name, style = MaterialTheme.typography.bodySmall)
                    },
                    onClick = {
                        item.onItemClicked()
                        onDismissRequest()
                    },
                    modifier = Modifier.background(color = Color.White)
                )
            }
        }
    }
}

data class DropDownMenuItem(
    val name: String,
    val onItemClicked: () -> Unit
)