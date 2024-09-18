package com.glitch.wedeliver.uix.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.glitch.wedeliver.R
import com.glitch.wedeliver.ui.theme.WeDeliverTheme
import com.glitch.wedeliver.ui.theme.Yellow1
import com.glitch.wedeliver.ui.theme.Yellow2
import kotlinx.coroutines.launch

@Composable
fun BottomBarScreen() {
	val selectedItem = remember { mutableStateOf(0) }
	val showBottomBar = remember { mutableStateOf(true) }
	val tabIndex = remember { mutableStateOf(0) }
	val scope = rememberCoroutineScope()
	val selectedColor: Color = Yellow1
	val unselectedColor: Color = Yellow2

	Scaffold(
		containerColor = Color.White,
		bottomBar = {
			if (showBottomBar.value) {
				BottomAppBar(
					containerColor = Color.Transparent,
					contentColor = Color.White,
					content = {
						NavigationBarItem(
							selected = selectedItem.value == 0,
							onClick = {
								selectedItem.value = 0
							},
							icon = {
								Icon(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "",
									tint = if (selectedItem.value == 0) selectedColor else unselectedColor
								)
							},
							label = {
								Text(
									text = "Mainpage",
									color = if (selectedItem.value == 0) selectedColor else unselectedColor
								)
							},
							colors = NavigationBarItemDefaults.colors(
								selectedIconColor = selectedColor,
								unselectedIconColor = unselectedColor,
								indicatorColor = Color.White
							)
						)

						NavigationBarItem(
							selected = selectedItem.value == 1,
							onClick = {
								selectedItem.value = 1
							},
							icon = {
								Icon(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "",
									tint = if (selectedItem.value == 1) selectedColor else unselectedColor
								)
							},
							label = {
								Text(
									text = "Category",
									color = if (selectedItem.value == 1) selectedColor else unselectedColor
								)
							},
							colors = NavigationBarItemDefaults.colors(
								selectedIconColor = selectedColor,
								unselectedIconColor = unselectedColor,
								indicatorColor = Color.White
							)
						)

						NavigationBarItem(
							selected = selectedItem.value == 2,
							onClick = {
								selectedItem.value = 2
							},
							icon = {
								Icon(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "",
									tint = if (selectedItem.value == 2) selectedColor else unselectedColor
								)
							},
							label = {
								Text(
									text = "Favorites",
									color = if (selectedItem.value == 2) selectedColor else unselectedColor
								)
							},
							colors = NavigationBarItemDefaults.colors(
								selectedIconColor = selectedColor,
								unselectedIconColor = unselectedColor,
								indicatorColor = Color.White
							)
						)

						NavigationBarItem(
							selected = selectedItem.value == 3,
							onClick = {
								selectedItem.value = 3
							},
							icon = {
								Icon(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "",
									tint = if (selectedItem.value == 3) selectedColor else unselectedColor
								)
							},
							label = {
								Text(
									text = "Cart",
									color = if (selectedItem.value == 3) selectedColor else unselectedColor
								)
							},
							colors = NavigationBarItemDefaults.colors(
								selectedIconColor = selectedColor,
								unselectedIconColor = unselectedColor,
								indicatorColor = Color.White
							)
						)

						NavigationBarItem(
							selected = selectedItem.value == 4,
							onClick = {
								selectedItem.value = 4
							},
							icon = {
								Icon(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "",
									//tint = if (selectedItem.value == 4) selectedColor else unselectedColor
								)
							},
							label = {
								Text(
									text = "Account",
									color = if (selectedItem.value == 4) selectedColor else unselectedColor
								)
							},
							colors = NavigationBarItemDefaults.colors(
								selectedIconColor = selectedColor,
								unselectedIconColor = unselectedColor,
								indicatorColor = Color.White
							)
						)
					})
			}
		}) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			verticalArrangement = Arrangement.SpaceEvenly,
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			when (selectedItem.value) {
				0 -> {
					Column {
						TabRow(
							selectedTabIndex = tabIndex.value, Modifier.background(Color.White)
						) {
							Tab(text = { Text("Çiçek ve Çikolata") }, icon = {
								Image(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "Search"
								)
							}, selected = tabIndex.value == 0, onClick = {
								scope.launch { tabIndex.value = 0 }
							})
							Tab(text = { Text("Presents") }, icon = {
								Image(
									painter = painterResource(id = R.drawable.search_vector),
									contentDescription = "Search"
								)
							}, selected = tabIndex.value == 1, onClick = {
								scope.launch { tabIndex.value = 1 }
							})
						}
						when (tabIndex.value) {
							0 -> PageSwitch(chosenPage = "Mainpage")
							1 -> Text("Presents content")
						}
					}
				}

				1 -> PageSwitch(chosenPage = "categorypage")
				2 -> {
					Column {
						TabRow(
							selectedTabIndex = tabIndex.value, Modifier.background(Color.White)
						) {
							Tab(text = { Text(stringResource(id = R.string.placeholder)) },
								selected = tabIndex.value == 0, onClick = {
									scope.launch { tabIndex.value = 0 }
								})
							Tab(text = { Text(stringResource(id = R.string.placeholder)) },
								selected = tabIndex.value == 1, onClick = {
									scope.launch { tabIndex.value = 1 }
								})
						}
						when (tabIndex.value) {
							0 -> PageSwitch(chosenPage = "favoritespage")
							1 -> Text("Presents content")
						}
					}
				}

				3 -> {
					showBottomBar.value = false
					CartPage() /*{
						*//*showBottomBar.value = true
						selectedItem.value = 0*//*
					}*/
				}

				4 -> {
					PageSwitch(chosenPage = "accountpage")
				}
			}
		}
	}
}

@Preview(
	showBackground = true
)
@Composable
fun BottomBarPreview() {
	WeDeliverTheme {
		BottomBarScreen()
	}
}