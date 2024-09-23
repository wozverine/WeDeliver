package com.glitch.wedeliver.uix.views

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.glitch.wedeliver.R
import com.glitch.wedeliver.data.entitiy.Foods
import com.glitch.wedeliver.ui.theme.WeDeliverTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainpage() {
	val menuList = remember { mutableStateListOf<Foods>() }

	LaunchedEffect(key1 = true) {
		val f1 = Foods(1, "Ayran", "img", 40)
		val f2 = Foods(2, "Kadayıf", "img", 50)
		val f3 = Foods(3, "Izgara", "img", 60)
		val f4 = Foods(4, "Tavuk", "img", 70)
		val f5 = Foods(5, "Et", "img", 80)
		val f6 = Foods(6, "Yemek", "img", 90)

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
					Text(text = "WeDeliver")
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

			val (rowsearch, LazyVerticalGrid) = createRefs()
			/*Column(
				modifier = Modifier
					.padding(paddingValues)
					.fillMaxSize()
					.background(Color.Blue)
			) {*/


			Row(
				modifier = Modifier
					.padding(vertical = 5.dp)
					.fillMaxWidth()


					//,.size(20.dp)
					.constrainAs(rowsearch) {
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						top.linkTo(parent.top)
						bottom.linkTo(LazyVerticalGrid.top)
					},
				horizontalArrangement = Arrangement.SpaceEvenly
			) {
				Image(
					painter = painterResource(id = R.drawable.searchbar_2),
					contentScale = ContentScale.Fit,
					contentDescription = null,
				)

				Image(
					painter = painterResource(id = R.drawable.side_search),
					contentScale = ContentScale.Fit,
					contentDescription = null,
				)

			}


			LazyVerticalGrid(
				columns = GridCells.Fixed(2),
				modifier = Modifier
					.fillMaxWidth()
					//.padding(paddingValues)
					.constrainAs(LazyVerticalGrid) {
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						top.linkTo(rowsearch.bottom)
						//bottom.linkTo(parent.bottom)
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
							val (main, firstImage, textImage, text, price) = createRefs()
							val activity = LocalContext.current as Activity

							Image(
								modifier = Modifier
									.constrainAs(main) {
										start.linkTo(parent.start)
										end.linkTo(parent.end)
										top.linkTo(parent.top)
										bottom.linkTo(parent.bottom)
									},
								painter = painterResource(R.drawable.base_main_card),
								contentDescription = ""
							)

							Image(
								modifier = Modifier
									.padding(vertical = 5.dp)
									.constrainAs(firstImage) {
										start.linkTo(parent.start)
										end.linkTo(parent.end)
										top.linkTo(parent.top)
										bottom.linkTo(textImage.top)
									}, bitmap = ImageBitmap.imageResource(
									id = activity.resources.getIdentifier(
										menu.yemek_resim_adi, "drawable", activity.packageName
									)
								), contentDescription = ""
							)

							Image(
								painter = painterResource(id = R.drawable.base_main_title),
								modifier = Modifier
									.fillMaxSize()
									.constrainAs(textImage) {
										start.linkTo(parent.start)
										end.linkTo(parent.end)
										top.linkTo(firstImage.bottom)
										//bottom.linkTo(parent.bottom)
									},
								contentDescription = ""
							)

							Text(
								modifier = Modifier
									.padding(vertical = 4.dp)
									.constrainAs(text) {
										start.linkTo(parent.start)
										end.linkTo(parent.end)
										top.linkTo(textImage.top)
										bottom.linkTo(textImage.bottom)
									},
								text = menu.yemek_adi,
								fontSize = 14.sp,
								color = Color.Black
							)


							Row(
								modifier = Modifier
									.fillMaxWidth()
									.padding(vertical = 4.dp, horizontal = 10.dp)
									.constrainAs(price) {
										start.linkTo(parent.start)
										end.linkTo(parent.end)
										top.linkTo(text.bottom)
										bottom.linkTo(parent.bottom)
									},
								horizontalArrangement = Arrangement.SpaceEvenly,
								verticalAlignment = Alignment.CenterVertically
							) {
								Text(text = "270 TL")

								Image(
									painter = painterResource(id = R.drawable.add_button_main),
									contentDescription = ""
								)
							}
						}
					}


				}

			}

			/*DrawableBoxButton(
				painterResource(id = R.drawable.cart_confirm),
				onClick = {
					println("DrawableButton clicked!")
				},
				modifier = Modifier.constrainAs(cartbutton) {
					start.linkTo(parent.start, margin = 16.dp)
					end.linkTo(parent.end, margin = 16.dp)
					bottom.linkTo(parent.bottom, margin = 16.dp)
				}
			)*/
		}
	}
}


@Preview(showBackground = true)
@Composable
fun Prev() {
	WeDeliverTheme {
		Mainpage()
	}
}