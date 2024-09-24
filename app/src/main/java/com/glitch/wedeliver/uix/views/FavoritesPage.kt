package com.glitch.wedeliver.uix.views

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.glitch.wedeliver.R
import com.glitch.wedeliver.data.entity.FoodItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesPage() {
	val menuList = remember { mutableStateListOf<FoodItem>() }

	LaunchedEffect(key1 = true) {
		val f1 = FoodItem(1, "Ayran", "img", 40, 2)
		val f2 = FoodItem(2, "Kadayıf", "img", 5, 20)
		val f3 = FoodItem(3, "Izgara", "img", 60, 2)
		val f4 = FoodItem(4, "Tavuk", "img", 70, 2)
		val f5 = FoodItem(5, "Et", "img", 80, 2)
		val f6 = FoodItem(6, "Yemek", "img", 90, 2)

		menuList.add(f1)
		menuList.add(f2)
		menuList.add(f3)
		menuList.add(f4)
		menuList.add(f5)
		menuList.add(f6)
	}

	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = {
					Text(text = "Favoriler")
				},
				actions = {}
			)
		}

	) { paddingValues ->

		ConstraintLayout(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {

			val (LazyColumn, CartButton) = createRefs()
			androidx.compose.foundation.lazy.LazyColumn(
				//columns = GridCells.Fixed(2),
				modifier = Modifier
					.fillMaxSize()
					//.padding(paddingValues)
					.constrainAs(LazyColumn) {
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						top.linkTo(parent.top)
						//bottom.linkTo(LazyVerticalGrid.top)
					},
				//.background(Color.White)
			) {
				items(count = menuList.count()) { index ->
					val menu = menuList[index]

					Card(
						modifier = Modifier
							.padding(vertical = 5.dp),
						colors = CardDefaults.cardColors(
							containerColor = Color.White
						)
					) {
						ConstraintLayout(
							modifier = Modifier
								.fillMaxWidth()
						) {
							val (mainback, firstImage, textImage, row, rowsecond) = createRefs()
							val activity = LocalContext.current as Activity

							Image(
								modifier = Modifier
									.constrainAs(mainback) {
										start.linkTo(parent.start)
										end.linkTo(parent.end)
										top.linkTo(parent.top)
										bottom.linkTo(parent.bottom)
									},
								painter = painterResource(R.drawable.cart_card),
								contentDescription = "",
								contentScale = ContentScale.FillWidth
							)

							Image(
								modifier = Modifier
									.padding(vertical = 5.dp)
									.constrainAs(firstImage) {
										start.linkTo(mainback.start)
										//end.linkTo(mainback.end)
										top.linkTo(mainback.top)
										bottom.linkTo(mainback.bottom)
									}, bitmap = ImageBitmap.imageResource(
									id = activity.resources.getIdentifier(
										menu.yemek_resim_adi, "drawable", activity.packageName
									)
								), contentDescription = ""
							)

							Column(
								modifier = Modifier
									.constrainAs(row) {
										start.linkTo(firstImage.end)
										//end.linkTo(parent.end)
										end.linkTo(rowsecond.start)
										top.linkTo(mainback.top)
										bottom.linkTo(mainback.bottom)
									},
								verticalArrangement = Arrangement.SpaceEvenly,
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								Text(
									modifier = Modifier
										.padding(vertical = 4.dp),
									text = menu.yemek_adi,
									fontSize = 20.sp,
									color = Color.Black,
									textAlign = TextAlign.Center
								)

								Text(
									text = "270 TL",
									fontSize = 16.sp,
									textAlign = TextAlign.Center
								)
							}

							Column(
								modifier = Modifier
									.padding(end = 30.dp)
									.constrainAs(rowsecond) {
										//start.linkTo(row.start)
										end.linkTo(mainback.end)
										top.linkTo(mainback.top)
										bottom.linkTo(mainback.bottom)
									},
								verticalArrangement = Arrangement.SpaceEvenly,
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								Text(
									modifier = Modifier
										.padding(vertical = 4.dp),
									text = "2",
									fontSize = 16.sp,
									color = Color.Black
								)

								Row(
									modifier = Modifier
										.padding(vertical = 10.dp),
								) {
									Image(
										painter = painterResource(id = R.drawable.cart_plus),
										contentDescription = ""
									)
									Image(
										painter = painterResource(id = R.drawable.cart_minus),
										contentDescription = ""
									)
								}
							}
						}
					}
				}
			}

		}
	}
}