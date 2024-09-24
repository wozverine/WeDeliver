package com.glitch.wedeliver.uix.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.glitch.wedeliver.R
import com.glitch.wedeliver.uix.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(cartViewModel: CartViewModel) {
	@Composable
	fun DrawableBoxButton(
		drawable: Painter,
		onClick: () -> Unit,
		modifier: Modifier = Modifier
	) {
		Box(
			modifier = modifier
				.clickable(onClick = onClick),
			contentAlignment = Alignment.Center
		) {
			Image(
				painter = drawable,
				contentDescription = "Box Button Icon",
			)
		}
	}

	val menuList = cartViewModel.cartList.observeAsState(listOf())

	if (menuList.value.isEmpty()) {
		println("No Cart item available")
	}

	/*LaunchedEffect(key1 = true) {
		cartViewModel.cartScan()
	}*/

	LaunchedEffect(menuList.value) {
		cartViewModel.cartScan()
	}


	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = {
					Text(text = "Sepetim")
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

			val (LazyColumn, CartButton, CartPrice) = createRefs()
			if (menuList.value.isEmpty()) {
				Log.d("Cart", "Cart empty")
			}
			LazyColumn(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 60.dp)
					.constrainAs(LazyColumn) {
						start.linkTo(parent.start)
						end.linkTo(parent.end)
						top.linkTo(parent.top)
						bottom.linkTo(CartButton.top)
					}

			) {
				items(
					count = menuList.value.count(),
					//) { index ->

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
								val (mainback, firstImage, row, rowsecond) = createRefs()

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
											top.linkTo(mainback.top)
											bottom.linkTo(mainback.bottom)
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
									contentDescription = ""
								)

								Column(
									modifier = Modifier
										.constrainAs(row) {
											start.linkTo(firstImage.end)
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
										text = "${menu.yemek_fiyat} TL",
										fontSize = 16.sp,
										textAlign = TextAlign.Center
									)
								}

								Column(
									modifier = Modifier
										.padding(end = 30.dp)
										.constrainAs(rowsecond) {
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
										text = menu.yemek_siparis_adet,
										fontSize = 16.sp,
										color = Color.Black
									)

									Row(
										modifier = Modifier
											.padding(vertical = 10.dp),
									) {
										DrawableBoxButton(
											drawable = painterResource(id = R.drawable.cart_plus),
											onClick = {
												cartViewModel.saveCart(
													menu.yemek_adi,
													menu.yemek_resim_adi,
													menu.yemek_fiyat.toInt(),
													menu.yemek_siparis_adet.toInt()
												)
											})
										DrawableBoxButton(
											drawable = painterResource(id = R.drawable.cart_minus),
											onClick = {
												cartViewModel.deleteCart(menu.sepet_yemek_id.toInt())
											})
									}
								}
							}

						}
					}
				)
			}

			DrawableBoxButton(
				painterResource(id = R.drawable.cart_confirm),
				onClick = {
					println("DrawableButton clicked!")/*TODO Lottie*/
				},
				modifier = Modifier
					.constrainAs(CartButton) {
						top.linkTo(LazyColumn.bottom, margin = 10.dp)
						start.linkTo(parent.start, margin = 16.dp)
						end.linkTo(CartPrice.start, margin = 16.dp)
						bottom.linkTo(parent.bottom, margin = 8.dp)
					}
			)

			Text(
				text = "500 TL", /*TODO*/
				fontSize = 20.sp,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.constrainAs(CartPrice) {
						top.linkTo(LazyColumn.bottom, margin = 10.dp)
						start.linkTo(CartButton.end, margin = 16.dp)
						end.linkTo(parent.end, margin = 16.dp)
						bottom.linkTo(parent.bottom, margin = 8.dp)
					}
			)
		}
	}
}
