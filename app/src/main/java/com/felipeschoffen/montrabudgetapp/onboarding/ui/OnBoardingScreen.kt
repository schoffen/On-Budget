package com.felipeschoffen.montrabudgetapp.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipeschoffen.montrabudgetapp.R
import com.felipeschoffen.montrabudgetapp.core.ui.buttons.CustomButtonPrimary
import com.felipeschoffen.montrabudgetapp.core.ui.buttons.CustomButtonSecondary

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val pagerState = rememberPagerState(pageCount = { 3 })

        HorizontalPager(pagerState) { page ->
            when (page) {
                0 -> OnBoardingPagerContent(
                    image = R.drawable.illustration_1,
                    title = R.string.onboarding_illustration_1_title,
                    description = R.string.onboarding_illustration_1_description
                )

                1 -> OnBoardingPagerContent(
                    image = R.drawable.illustration_2,
                    title = R.string.onboarding_illustration_2_title,
                    description = R.string.onboarding_illustration_2_description
                )

                2 -> OnBoardingPagerContent(
                    image = R.drawable.illustration_3,
                    title = R.string.onboarding_illustration_3_title,
                    description = R.string.onboarding_illustration_3_description
                )
            }
        }

        OnBoardingPagerIndicator(
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage
        )

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomButtonPrimary(text = stringResource(R.string.sign_up), onClick = { })

            CustomButtonSecondary(text = stringResource(R.string.login), onClick = { })
        }
    }
}