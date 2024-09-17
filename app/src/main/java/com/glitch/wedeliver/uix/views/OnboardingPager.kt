package com.glitch.wedeliver.uix.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.glitch.wedeliver.ui.theme.WeDeliverTheme

@Composable
fun OnboardingPager(onComplete: () -> Unit) {
	val pagerState = rememberPagerState(pageCount = { 3 })

	HorizontalPager(
		state = pagerState,
		modifier = Modifier.fillMaxSize()
	) { page ->
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(text = "Onboarding Page $page")
			if (page == 2) {
				Button(
					onClick = onComplete,
					modifier = Modifier.padding(16.dp)
				) {
					Text("Get Started")
				}
			}
		}
	}
}

@Preview(showBackground = true, locale = "tr")
@Composable
fun Pre() {
	WeDeliverTheme {
		OnboardingPager(onComplete = {})
	}
}