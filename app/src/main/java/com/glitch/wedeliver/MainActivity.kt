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

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
		val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

		enableEdgeToEdge()
		setContent {
			val navController = rememberNavController()

			NavHost(
				navController = navController,
				startDestination = if (isFirstLaunch) "onboarding" else "bottombar"
			) {
				composable("onboarding") {
					OnboardingPager(navController = navController)
				}
				composable("bottombar") {
					BottomBarScreen()
				}
			}

			if (isFirstLaunch) {
				OnboardingPager(navController = navController) /*{
					sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
					//BottomBarScreen()
				}*/
			} else {
				OnboardingPager(navController) /*{
					sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
					//BottomBarScreen()
				}*/
				//BottomBarScreen()
			}
		}
	}
}
