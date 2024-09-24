package com.glitch.wedeliver.data.entity


data class CartItem(
	var sepet_yemek_id: String,
	var yemek_adi: String,
	var yemek_resim_adi: String,
	var yemek_fiyat: String,
	var yemek_siparis_adet: String,
	var kullanici_adi: String,
) {
	/*fun addQuantity(amount: Int) {
		yemek_siparis_adet = ((yemek_siparis_adet.toInt()) + amount).toString()
	}*/
}
