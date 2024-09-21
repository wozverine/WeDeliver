package com.glitch.wedeliver.uix.views

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.glitch.wedeliver.ui.theme.WeDeliverTheme

@Composable
fun OnboardingPager(
	navController: NavController,
	onComplete: () -> Unit
) {
	val pagerState = rememberPagerState(pageCount = { 3 })

	/*val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
	val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)*/

	HorizontalPager(
		state = pagerState,
		modifier = Modifier.fillMaxSize()
	) { page ->
		Box(
			modifier = Modifier
				.background(Color.White)
				.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Text(text = "Onboarding Page $page")
			if (page == 2) {
				Button(
					onClick = {
						onComplete()
						navController.navigate("bottombarscreen") {
							popUpTo("onboarding") { inclusive = true }
						}

						/*NavHost(
				navController = navController,
				startDestination = if (isFirstLaunch) "onboarding" else "bottombar"
			) {
				composable("onboarding") {
					OnboardingPager(navController = navController)
				}
				composable("bottombar") {
					BottomBarScreen()
				}
			}*/

						/*navController.navigate("bottombarscreen") {
							popUpTo("onboarding") {
								inclusive = true
							}
						}*/
						//Log.v("mainpage", "clicked")
						/*navController.context.getSharedPreferences(
							"MyAppPrefs",
							Context.MODE_PRIVATE
						)
							.edit().putBoolean("isFistLaunch", false).apply()*/
					},
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
	val navController = rememberNavController()
	WeDeliverTheme {
		OnboardingPager(navController, onComplete = { })
	}
}