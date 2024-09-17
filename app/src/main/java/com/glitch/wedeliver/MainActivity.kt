package com.glitch.wedeliver

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.glitch.wedeliver.uix.views.OnboardingPager

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
		val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

		enableEdgeToEdge()
		setContent {
			if (isFirstLaunch) {
				OnboardingPager {
					sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
					//BottomBarScreen()
				}
			} else {
				OnboardingPager {
					sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
					//BottomBarScreen()
				}
				//BottomBarScreen()
			}
		}
	}
}
