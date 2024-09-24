package com.glitch.wedeliver.uix.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.glitch.wedeliver.R
import com.glitch.wedeliver.data.entity.CartItem
import com.glitch.wedeliver.data.entity.FoodItem
import com.glitch.wedeliver.ui.theme.Yellow2
import com.glitch.wedeliver.uix.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
	nesne: FoodItem, detailViewModel: DetailViewModel
) {
	@Composable
	fun DrawableBoxButton(
		drawable: Painter,
		onClick: () -> Unit,
		modifier: Modifier = Modifier
	) {
		Box(
			modifier = modifier
				.size(50.dp)
				.clickable(onClick = onClick),
			contentAlignment = Alignment.Center
		) {
			Image(
				painter = drawable,
				contentDescription = "Box Button Icon",
				modifier = Modifier.size(50.dp)
			)
		}
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
				.padding(horizontal = 20.dp)
				.background(Yellow2)
		) {
			val (firstImage, nameText, priceText, row) = createRefs()

			Image(
				modifier = Modifier
					.size(200.dp)
					.padding(vertical = 5.dp)
					.constrainAs(firstImage) {
						start.linkTo(parent.start)
						top.linkTo(parent.top)
						end.linkTo(parent.end)
					},
				painter = rememberAsyncImagePainter(
					model = ImageRequest.Builder(LocalContext.current)
						.data("http://kasimadalan.pe.hu/yemekler/resimler/${nesne.yemek_resim_adi}")
						.crossfade(true)
						.transformations(CircleCropTransformation())
						.error(R.drawable.img) /*// Replace with a local error drawable TODO*/
						.placeholder(R.drawable.img) /*// Replace with a local error drawable TODO*/
						.listener(
							onStart = {
								Log.d("Image Loading", "Image Loading")
								Log.d(
									"Image URL",
									"http://kasimadalan.pe.hu/yemekler/resimler/${nesne.yemek_resim_adi}"
								)
							},
							onError = { _, throwable ->
								Log.e("Image Error", "Error")
							},
							onSuccess = { _, _ ->
								Log.d(
									"Image Loaded",
									"Image loaded for ${nesne.yemek_adi}"
								)
							}
						)
						.build()
				),
				contentDescription = ""
			)

			Text(
				text = nesne.yemek_adi,
				fontSize = 35.sp,
				modifier = Modifier
					.constrainAs(nameText) {
						start.linkTo(parent.start)
						//end.linkTo(parent.end)
						top.linkTo(firstImage.bottom)
					}
					.padding(20.dp)
			)

			Text(
				text = "${nesne.yemek_fiyat} TL",
				fontSize = 35.sp,
				modifier = Modifier
					.constrainAs(priceText) {
						start.linkTo(parent.start)
						//end.linkTo(parent.end)
						top.linkTo(nameText.bottom)
					}
					.padding(20.dp)
			)

			Row(
				modifier = Modifier
					.constrainAs(row) {
						end.linkTo(parent.end)
						top.linkTo(priceText.bottom)
					},
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.End
			) {
				Text(
					text = nesne.yemek_siparis_adet.toString(),
					fontSize = 25.sp,
					modifier = Modifier.padding(end = 20.dp)
				)

				DrawableBoxButton(
					drawable = painterResource(id = R.drawable.cart_plus),
					onClick = {
						val updatedQuantity = (nesne.yemek_siparis_adet.toInt() ?: 0) + 1
						val updatedItem =
							nesne.copy(yemek_siparis_adet = updatedQuantity)
						detailViewModel.saveCart(
							updatedItem.yemek_adi,
							updatedItem.yemek_resim_adi,
							updatedItem.yemek_fiyat.toInt(),
							updatedItem.yemek_siparis_adet.toInt()
						)
					}
				)

				/*DrawableBoxButton(
					drawable = painterResource(id = R.drawable.cart_minus),
					onClick = {
						val updatedQuantity = nesne.yemek_siparis_adet.toInt() - 1
						if (updatedQuantity > 0) {
							val updatedItem =
								nesne.copy(yemek_siparis_adet = updatedQuantity.toString())
							detailViewModel.updateCart(updatedItem)
						} else {
							detailViewModel.deleteCart(nesne.sepet_yemek_id.toInt())
						}
					}
				)*/
			}
		}
	}
}