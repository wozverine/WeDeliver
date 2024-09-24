package com.glitch.wedeliver.uix.views

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.glitch.wedeliver.R
import com.glitch.wedeliver.uix.viewmodel.MainpageViewModel
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainpage(navController: NavController, mainpageViewModel: MainpageViewModel) {
	@Composable
	fun DrawableBoxButton(
		drawable: Painter,
		onClick: () -> Unit,
		modifier: Modifier = Modifier
	) {
		Box(
			modifier = modifier
				//.size(56.dp)
				//.fillMaxWidth()
				.clickable(onClick = onClick),
			contentAlignment = Alignment.Center
		) {
			Image(
				painter = drawable,
				contentDescription = "Box Button Icon",
				//modifier = Modifier.size(24.dp)
			)
		}
	}

	val menuList = mainpageViewModel.foodList.observeAsState(listOf())

	LaunchedEffect(key1 = true) {
		mainpageViewModel.foodScan()
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
			Row(
				modifier = Modifier
					.padding(vertical = 5.dp)
					.fillMaxWidth()
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
					.constrainAs(LazyVerticalGrid) {
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						top.linkTo(rowsearch.bottom)
						//bottom.linkTo(parent.bottom)
					},
				//.background(Color.White)
			) {
				items(
					count = menuList.value.count(),

					itemContent = {
						val menu = menuList.value[it]

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
										},
									painter = rememberAsyncImagePainter(
										model = ImageRequest.Builder(LocalContext.current)
											.data("http://kasimadalan.pe.hu/yemekler/resimler/${menu.yemek_resim_adi}")
											.crossfade(true)
											.transformations(CircleCropTransformation())
											.error(R.drawable.img) /*// Replace with a local error drawable TODO*/
											.placeholder(R.drawable.img) /*// Replace with a local error drawable TODO*/
											.listener(
												onStart = {
													Log.d("Image Loading", "Image Loading")
													Log.d(
														"Image URL",
														"http://kasimadalan.pe.hu/yemekler/resimler/${menu.yemek_resim_adi}"
													)
												},
												onError = { _, throwable ->
													Log.e("Image Error", "Error")
												},
												onSuccess = { _, _ ->
													Log.d(
														"Image Loaded",
														"Image loaded for ${menu.yemek_adi}"
													)
												}
											)
											.build()
									),
									contentDescription = menu.yemek_adi,
									contentScale = ContentScale.Crop
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
									fontSize = 15.sp,
									color = Color.Black
								)


								Row(
									modifier = Modifier
										.fillMaxWidth()
										.padding(vertical = 4.dp, horizontal = 10.dp)
										.constrainAs(price) {
											start.linkTo(main.start)
											end.linkTo(main.end)
											top.linkTo(text.bottom)
											bottom.linkTo(main.bottom)
										},
									horizontalArrangement = Arrangement.SpaceEvenly,
									verticalAlignment = Alignment.CenterVertically
								) {
									Text(
										textAlign = TextAlign.Center,
										fontSize = 15.sp,
										text = "${menu.yemek_fiyat} TL"
									)

									DrawableBoxButton(
										drawable = painterResource(id = R.drawable.add_button_main),
										onClick = {
											val menuJson = Gson().toJson(menu)
											navController.navigate("detailpage/$menuJson")
											/*mainpageViewModel.saveCart(
												menu.yemek_adi,
												menu.yemek_resim_adi,
												menu.yemek_fiyat,
												(menu.yemek_siparis_adet)+1
											)*/
										})
								}
							}
						}
					}
				)
			}
		}
	}
}
