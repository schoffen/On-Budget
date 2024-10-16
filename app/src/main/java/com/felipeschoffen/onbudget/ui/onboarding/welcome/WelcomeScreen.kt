package com.felipeschoffen.onbudget.ui.onboarding.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.felipeschoffen.onbudget.R
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonPrimary
import com.felipeschoffen.onbudget.ui.core.buttons.CustomButtonSecondary
import com.felipeschoffen.onbudget.ui.onboarding.welcome.components.OnBoardingPagerContent
import com.felipeschoffen.onbudget.ui.onboarding.welcome.components.OnBoardingPagerIndicator

@Composable
fun WelcomeScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val pagerState = rememberPagerState(pageCount = { 3 })

        HorizontalPager(pagerState) { page ->
            when (page) {
                0 -> OnBoardingPagerContent(
                    image = R.drawable.control_illustration,
                    title = R.string.onboarding_control_title,
                    description = R.string.onboarding_control_description
                )

                1 -> OnBoardingPagerContent(
                    image = R.drawable.tracking_illustration,
                    title = R.string.onboarding_tracking_title,
                    description = R.string.onboarding_tracking_description
                )

                2 -> OnBoardingPagerContent(
                    image = R.drawable.planning_illustration,
                    title = R.string.onboarding_planning_title,
                    description = R.string.onboarding_planning_description
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
            CustomButtonPrimary(text = stringResource(R.string.sign_up_title), onClick = onRegisterClicked)

            CustomButtonSecondary(text = stringResource(R.string.login_title), onClick = onLoginClicked)
        }
    }
}