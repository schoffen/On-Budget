package com.felipeschoffen.montrabudgetapp.login.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.felipeschoffen.montrabudgetapp.R

@Composable
fun SignUpNoAccountButton(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                color = MaterialTheme.colorScheme.onBackground
            )
        ) {
            append(stringResource(R.string.signup_no_account))
            append(" ")
        }
        // TODO: Switch this when implementing navigation
        withLink(
            link = LinkAnnotation.Url(
                "https://www.google.com",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            )
        ) {
            append(stringResource(R.string.sign_up_title))
        }
    })
}