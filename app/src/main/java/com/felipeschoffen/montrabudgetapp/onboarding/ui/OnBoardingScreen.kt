package com.felipeschoffen.montrabudgetapp.onboarding.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState(pageCount = { 3 })

        HorizontalPager(pagerState) {
            PagerContent()
        }
    }
}

@Preview
@Composable
fun PreviewOnBoardingScreen(modifier: Modifier = Modifier) {

}