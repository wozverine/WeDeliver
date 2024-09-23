package com.glitch.wedeliver.data.repo

import com.glitch.wedeliver.data.datasource.FoodDataSource
import com.glitch.wedeliver.data.entitiy.Foods

class FoodsRepository(var fds: FoodDataSource) {

	suspend fun saveCart(
		yemek_adi: String,
		yemek_resim_adi: String,
		yemek_fiyat: Int,
		food_count: Int,
		kullanici_adi: String
	) = fds.saveCart(yemek_adi, yemek_resim_adi, yemek_fiyat, food_count, kullanici_adi)

	/*suspend fun updateCart(food_id: Int, yemek_adi: String, yemek_fiyat: String) =
		fds.updateCart(food_id, yemek_adi, yemek_fiyat)*/

	suspend fun deleteCart(sepet_yemek_id: Int, kullanici_adi:String) = fds.deleteCart(sepet_yemek_id, kullanici_adi)

	suspend fun foodScan(): List<Foods> = fds.foodScan()

	//suspend fun search(queryString: String): List<Foods> = fds.search(queryString)
}