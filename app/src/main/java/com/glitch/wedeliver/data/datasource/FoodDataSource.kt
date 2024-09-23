package com.glitch.wedeliver.data.datasource

import com.glitch.wedeliver.data.entitiy.Foods
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
		fdao.saveCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
	}

	/*suspend fun updateCart(yemek_id: Int, yemek_adi: String, yemek_fiyat: String) {
		fdao.updateCart(yemek_id, yemek_adi, yemek_fiyat)
	}*/

	suspend fun deleteCart(sepet_yemek_id: Int, kullanici_adi:String) {
		fdao.deleteCart(sepet_yemek_id,kullanici_adi)
	}

	suspend fun foodScan(): List<Foods> = withContext(Dispatchers.IO) {
		return@withContext fdao.foodScan().foods
	}

	/*suspend fun search(queryString: String): List<Foods> = withContext(Dispatchers.IO) {
		return@withContext fdao.search(queryString).foods
	}*/

}

