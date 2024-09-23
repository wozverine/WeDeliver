package com.glitch.wedeliver.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PageSwitch(chosenPage: String) {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = chosenPage) {
		composable("mainpage") {
			Mainpage(
				//	navController = navController
			)
		}
		composable("detailpage") {
			DetailPage()
		}
		composable("cartpage") {
			CartPage(
				//navigateBack = { navController.popBackStack() }
			)
		}

		composable("bottombarscreen") {
			BottomBarScreen()
		}

		composable("onboarding") {
			OnboardingPager(navController){}
		}

		composable("favoritespage") {
			FavoritesPage()
		}
	}
}
