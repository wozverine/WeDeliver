package com.glitch.wedeliver.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.glitch.wedeliver.data.entity.CartItem
import com.glitch.wedeliver.uix.viewmodel.CartViewModel
import com.glitch.wedeliver.uix.viewmodel.DetailViewModel
import com.glitch.wedeliver.uix.viewmodel.MainpageViewModel
import com.google.gson.Gson

@Composable
fun PageSwitch(
	mainpageViewModel: MainpageViewModel,
	cartViewModel: CartViewModel,
	detailViewModel: DetailViewModel,
	chosenPage: String
) {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = chosenPage) {
		composable("mainpage") {
			Mainpage(
				mainpageViewModel = mainpageViewModel,
				navController = navController
			)
		}
		composable(
			route = "detailpage/{foodInfo}",
			//DetailPage()
			arguments = listOf(
				navArgument("foodInfo") { type = NavType.StringType }
			)
		)
		{
			val json = it.arguments?.getString("foodInfo")
			val nesne = Gson().fromJson(json, CartItem::class.java)
			DetailPage(nesne, detailViewModel)
		}
		composable("cartpage") {
			CartPage(
				cartViewModel
			)
		}

		composable("bottombarscreen") {
			BottomBarScreen(
				mainpageViewModel = mainpageViewModel,
				cartViewModel = cartViewModel,
				detailViewModel = detailViewModel
			)
		}

		composable("onboarding") {
			OnboardingPager(navController) {}
		}

		composable("favoritespage") {
			FavoritesPage()
		}
	}
}
