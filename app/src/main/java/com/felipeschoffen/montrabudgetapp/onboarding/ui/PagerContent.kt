package com.felipeschoffen.montrabudgetapp.onboarding.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.R

@Composable
fun PagerContent(
    @DrawableRes image: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    )
    {
        Image(painter = painterResource(image), contentDescription = null)
        Text(
            text = stringResource(title),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(description),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

    }
}

@Preview
@Composable
fun PreviewPagerContent(modifier: Modifier = Modifier) {
    PagerContent(
        image = R.drawable.illustration_1,
        title = R.string.onboarding_illustration_1_title,
        description = R.string.onboarding_illustration_1_description
    )
}