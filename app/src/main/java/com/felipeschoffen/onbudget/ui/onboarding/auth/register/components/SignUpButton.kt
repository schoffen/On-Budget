package com.felipeschoffen.onbudget.ui.onboarding.auth.register.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean
) {
    CustomButtonPrimary(
        modifier = modifier,
        onClick = onClick,
        text = stringResource(R.string.sign_up_title),
        showLoadingProgress = isLoading
    )
}