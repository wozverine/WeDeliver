package com.glitch.wedeliver

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glitch.wedeliver.uix.views.BottomBarScreen
import com.glitch.wedeliver.uix.views.OnboardingPager
import com.glitch.wedeliver.uix.views.PageSwitch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
		val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)


		enableEdgeToEdge()
		setContent {
			val navController = rememberNavController()
			if (isFirstLaunch) {
				OnboardingPager(navController = navController) {
					sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
				}
				//PageSwitch("bottombar")
			} else {
				PageSwitch("bottombarscreen")
			}
			NavHost(
				navController = navController,
				startDestination = if (isFirstLaunch) "onboarding" else "bottombarscreen"
			) {
				composable("onboarding") {
					OnboardingPager(navController = navController) {
						sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
					}
				}
				composable("bottombarscreen") {
					BottomBarScreen()
				}
			}

			/*if (isFirstLaunch) {
				OnboardingPager(navController = navController)
				sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
			} else {
				BottomBarScreen()
				sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
				//BottomBarScreen()

				//BottomBarScreen()
			}*/
		}
	}
}
