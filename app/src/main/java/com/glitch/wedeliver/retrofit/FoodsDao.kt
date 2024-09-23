package com.glitch.wedeliver.retrofit

import com.glitch.wedeliver.data.entitiy.CRUDAnswer
import com.glitch.wedeliver.data.entitiy.FoodAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {
	@GET("yemekler/tumYemekleriGetir.php")
	suspend fun foodScan(): FoodAnswer

	@POST("yemekler/sepeteYemekEkle.php")
	@FormUrlEncoded
	suspend fun saveCart(
		@Field("yemek_adi") yemek_adi: String,
		@Field("yemek_resim_adi") yemek_resim_adi: String,
		@Field("yemek_fiyat") yemek_fiyat: Int,
		@Field("yemek_siparis_adet") yemek_siparis_adet: Int,
		@Field("kullanici_adi") kullanici_adi: String
	): CRUDAnswer

	/*@POST("yemekler/sepeteYemekEkle.php")
	@FormUrlEncoded
	suspend fun updateCart(
		@Field("kisi_id") kisi_id: Int,
		@Field("yemek_adi") yemek_adi: String,
		@Field("kisi_tel") kisi_tel: String
	): CRUDAnswer*/

	@POST("yemekler/sepettenYemekSil.php")
	@FormUrlEncoded
	suspend fun deleteCart(
		@Field("sepet_yemek_id") sepet_yemek_id: Int,
		@Field("kullanici_adi") kullanici_adi: String
	): CRUDAnswer

	/*@POST("kisiler/tum_kisiler_arama.php")
	@FormUrlEncoded
	suspend fun search(@Field("yemek_adi") queryString: String): FoodAnswer*/
}