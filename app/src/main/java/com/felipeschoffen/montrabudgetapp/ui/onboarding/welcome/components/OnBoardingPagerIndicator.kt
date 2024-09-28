package com.felipeschoffen.montrabudgetapp.ui.onboarding.welcome.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingPagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier
            .heightIn(min = 20.dp)
            .widthIn(min = 100.dp)
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            val size by animateDpAsState(
                if (currentPage == iteration) 16.dp else 8.dp,
                label = "",
                animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
            )

            Box(modifier = Modifier.size(16.dp), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(size)
                )
            }
        }
    }
}