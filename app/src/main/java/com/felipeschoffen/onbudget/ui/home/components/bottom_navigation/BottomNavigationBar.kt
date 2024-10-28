package com.felipeschoffen.onbudget.ui.home.components.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.felipeschoffen.onbudget.ui.core.theme.MontraBudgetAppTheme

@Composable
fun BottomNavigationBar(onClicked: (Any) -> Unit) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        bottomNavigationItems.forEachIndexed { index, item ->
            val isSelected = index == selectedItemIndex
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = stringResource(item.title),
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else LocalContentColor.current
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        color = if (isSelected) MaterialTheme.colorScheme.primary else LocalContentColor.current
                    )
                },
                selected = isSelected,
                onClick = {
                    selectedItemIndex = index
                    onClicked(item.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomAppBar(modifier: Modifier = Modifier) {
    MontraBudgetAppTheme {
        BottomNavigationBar({})
    }
}
