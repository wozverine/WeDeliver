package com.glitch.wedeliver.data.datasource

import android.util.Log
import com.glitch.wedeliver.data.entity.CartItem
import com.glitch.wedeliver.data.entity.FoodItem
import com.glitch.wedeliver.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(
	var fdao: FoodsDao
) {
	suspend fun saveCart(
		yemek_adi: String,
		yemek_resim_adi: String,
		yemek_fiyat: Int,
		yemek_siparis_adet: Int,
		kullanici_adi: String
	) {
		try {
			val response = fdao.saveCart(
				yemek_adi,
				yemek_resim_adi,
				yemek_fiyat,
				yemek_siparis_adet,
				kullanici_adi
			)
			Log.d("Cart API Response", "Success: ${response.success}, Message: ${response.message}")

			if (response.success == 1) {
				Log.d("Cart", "Item successfully added to cart.")
			} else {
				Log.d("Cart", "Failed to add item to cart: ${response.message}")
			}
		} catch (e: Exception) {
			Log.e("API Error", "Error adding item to cart: ${e.message}")
		}
	}

	suspend fun deleteCart(sepet_yemek_id: Int, kullanici_adi: String) {
		fdao.deleteCart(sepet_yemek_id, kullanici_adi)
	}


	suspend fun foodScan(): List<FoodItem> = withContext(Dispatchers.IO) {
		val foodAnswer = fdao.foodScan()
		Log.d("FoodDataSource", "FoodItemAnswer: $foodAnswer")
		return@withContext foodAnswer.yemekler
	}

	suspend fun cartScan(kullanici_adi: String): List<CartItem> = withContext(Dispatchers.IO) {
		val cartAnswer = fdao.cartScan(kullanici_adi)
		Log.d("FoodDataSource", "CartAnswer: $cartAnswer")
		return@withContext cartAnswer?.sepet_yemekler ?: emptyList()
	}


	/*suspend fun search(queryString: String): List<FoodItem> = withContext(Dispatchers.IO) {
		return@withContext fdao.search(queryString).foods
	}*/

}

