package com.glitch.wedeliver.data.repo

import android.util.Log
import com.glitch.wedeliver.data.datasource.FoodDataSource
import com.glitch.wedeliver.data.entity.CartItem
import com.glitch.wedeliver.data.entity.FoodItem

class FoodsRepository(var fds: FoodDataSource) {
	private val cartItems = mutableListOf<CartItem>()

	suspend fun saveCart(
		yemek_adi: String,
		yemek_resim_adi: String,
		yemek_fiyat: Int,
		yemek_siparis_adet: Int,
		kullanici_adi: String
	) = fds.saveCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

	suspend fun deleteCart(sepet_yemek_id: Int, kullanici_adi: String) =
		fds.deleteCart(sepet_yemek_id, kullanici_adi)

	suspend fun foodScan(): List<FoodItem> {
		val response = fds.foodScan()
		return response
	}

	suspend fun cartScan(): List<CartItem> {
		val response = fds.cartScan("irem_cingi")
		Log.d("CartRepository", "API Response cartScan: $response")
		return response
	}



	suspend fun updateCart(cartItem: CartItem) {
		val index = cartItems.indexOfFirst { it.sepet_yemek_id == cartItem.sepet_yemek_id }
		if (index != -1) {
			cartItems[index] = cartItem
		} else {
			cartItems.add(cartItem)
		}
	}


	//suspend fun search(queryString: String): List<FoodItem> = fds.search(queryString)
}