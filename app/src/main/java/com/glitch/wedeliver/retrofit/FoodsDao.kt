package com.glitch.wedeliver.retrofit

import com.glitch.wedeliver.data.entity.CRUDAnswer
import com.glitch.wedeliver.data.entity.CartItemAnswer
import com.glitch.wedeliver.data.entity.FoodItemAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {
	@GET("yemekler/tumYemekleriGetir.php")
	suspend fun foodScan(): FoodItemAnswer

	@POST("yemekler/sepettekiYemekleriGetir.php")
	@FormUrlEncoded
	suspend fun cartScan(
		@Field("kullanici_adi") kullanici_adi: String,
	): CartItemAnswer

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
		@Field("yemek_adi") yemek_adi: String,
		@Field("yemek_resim_adı") yemek_resim_adı: String,
		@Field("yemek_fiyat") yemek_fiyat: Int,
		@Field("yemek_siparis_adet") yemek_siparis_adet: Int,
		@Field("kullanici_adi") kullanici_adi: String,
	): CRUDAnswer*/

	@POST("yemekler/sepettenYemekSil.php")
	@FormUrlEncoded
	suspend fun deleteCart(
		@Field("sepet_yemek_id") sepet_yemek_id: Int,
		@Field("kullanici_adi") kullanici_adi: String
	): CRUDAnswer
}